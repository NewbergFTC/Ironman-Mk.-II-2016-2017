package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.lasarobotics.vision.android.Cameras;
import org.lasarobotics.vision.ftc.resq.Beacon;
import org.lasarobotics.vision.opmode.LinearVisionOpMode;
import org.lasarobotics.vision.opmode.extensions.CameraControlExtension;
import org.lasarobotics.vision.util.ScreenOrientation;
import org.opencv.core.Size;

//This is a test

/**
 * Created by Marcos on 11/16/2016.
 */

public abstract class IIOpMode extends LinearVisionOpMode {

    DcMotor leftback;
    DcMotor rightback;
    DcMotor shooter;
    int CLICKS_PER_REVOLUTION = 1120;

    protected void Clear()
    {
    }

    public IIOpMode()
    {
        super();
        leftback = null;
        rightback = null;
        shooter = null;
    }

    public void Init()
    {
        //Initializes the configuration for the rightfront = hardwareMap.dcMotor.get("rf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightback = hardwareMap.dcMotor.get("rb");
        shooter = hardwareMap.dcMotor.get("sh");


        leftback.setDirection(DcMotor.Direction.REVERSE); //Reverses the left back motor

        rightback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  //Sets up the robot ready for encoder use
        leftback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public  void Autonomous_Shoot(int SHOTS)
    {
        int GEAR_1 = 33;
        int GEAR_2 = 64;
        int revolution_of_flipper = (GEAR_2/GEAR_1)* (SHOTS * CLICKS_PER_REVOLUTION);
        int ShooterTargetValue = shooter.getCurrentPosition();
        int SHOTS_MADE = revolution_of_flipper * SHOTS;

        shooter.setTargetPosition((revolution_of_flipper * SHOTS) + ShooterTargetValue);

        shooter.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        shooter.setPower(1);

        while(Math.abs(shooter.getCurrentPosition()) < SHOTS_MADE)
        {
            telemetry.addData("Shot Value", shooter.getCurrentPosition());
            telemetry.addData("Target", SHOTS_MADE);
            //leftback.setPower(power);
            telemetry.update();
        }

    }

    public void StopDriving()
    {
        leftback.setPower(0);
        rightback.setPower(0);
    }


    public void TurnRight(double power,int TARGET_DEGREE) throws InterruptedException
    {
        int DEGERRE_PER_TICK = 1120/360;
        int RightTurnValue = rightback.getCurrentPosition();
        int LeftTurnValue = leftback.getCurrentPosition();
        int TARGET_TURN = DEGERRE_PER_TICK * TARGET_DEGREE;

        leftback.setTargetPosition(DEGERRE_PER_TICK * TARGET_DEGREE + LeftTurnValue);
        rightback.setTargetPosition(DEGERRE_PER_TICK * TARGET_DEGREE + RightTurnValue);

        rightback.setMode(DcMotor.RunMode.RUN_TO_POSITION); // the encoders are going to this postion
        leftback.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftback.setPower(power);
        rightback.setPower(-power);

        while(Math.abs(rightback.getCurrentPosition()) < TARGET_DEGREE)
        {
            telemetry.addData("Left Turn Value",leftback.getCurrentPosition());
            telemetry.addData("Right Turn Value",rightback.getCurrentPosition());
            telemetry.addData("Target", TARGET_DEGREE);
            //leftback.setPower(power);
            telemetry.update();
        }
        StopDriving();

    }
    public void TurnLeft(double power) throws InterruptedException
    {

    }
    public  void Reverse (double power) throws InterruptedException
    {
        leftback.setPower(-power);
        rightback.setPower(power);
        sleep(500);
        leftback.setPower(0);
        rightback.setPower(0);
    }
    // leftfront.getCurrentPosition(); //get the current postions of the motors
    // rightfront.getCurrentPosition();

    public void DriveForwardDistance(double power, int TARGET_GOAL)
    {
        telemetry.update();
        int RightValue = rightback.getCurrentPosition();
        int LeftValue = leftback.getCurrentPosition();


        rightback.setTargetPosition(TARGET_GOAL + RightValue); //sets the Target position for the motors
        leftback.setTargetPosition(TARGET_GOAL + LeftValue);

        rightback.setMode(DcMotor.RunMode.RUN_TO_POSITION); // the encoders are going to this postion
        leftback.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftback.setPower(power);         //sets the power to the Target value.
        rightback.setPower(power);

        while (Math.abs(rightback.getCurrentPosition()) < TARGET_GOAL)
        {
            telemetry.addData("Right", Math.abs(rightback.getCurrentPosition()));
            telemetry.addData("Left", Math.abs(leftback.getCurrentPosition()));
            telemetry.addData("Left & Right Goal",TARGET_GOAL);
            telemetry.update();
            //while the robot is going to the postion the encoders wont get any info
        }
        leftback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        StopDriving();
    }
    public void runOpMode() throws InterruptedException
    {
        waitForVisionStart();

        setCamera(Cameras.SECONDARY);
        setFrameSize(new Size(900, 900));

        enableExtension(Extensions.BEACON);
        enableExtension(Extensions.ROTATION);
        enableExtension(Extensions.CAMERA_CONTROL);

        beacon.setAnalysisMethod(Beacon.AnalysisMethod.COMPLEX);

        beacon.setColorToleranceRed(0);
        beacon.setColorToleranceBlue(-0.1);

        rotation.setIsUsingSecondaryCamera(true);
        rotation.disableAutoRotate();
        rotation.setActivityOrientationFixed(ScreenOrientation.PORTRAIT);

        cameraControl.setColorTemperature(CameraControlExtension.ColorTemperature.AUTO);
        cameraControl.setAutoExposureCompensation();

        Init();

        waitForStart();

        Run();

        Clear();
    }

    public int getBlueRightSide() throws InterruptedException
    {
        int results = 0;
        long TimeSlept = 0;

        while(opModeIsActive())
        {
            if(hasNewFrame())
            {
                Beacon.BeaconAnalysis beaconAnalysis = beacon.getAnalysis();

                results = beaconAnalysis.isRightBlue() ? 1 : 0;

                break;
            }
            else
            {
                if(TimeSlept > 10000)
                {
                    results = -1;

                    break;
                }

                TimeSlept += 10;
                sleep(10);
            }

            Update();
        }

        return results;
    }
    public void Update() throws InterruptedException
    {
        telemetry.update();
    }
    public  void Idle() throws InterruptedException
    {
        waitOneFullHardwareCycle();
    }
   abstract public void Run() throws InterruptedException;
}
