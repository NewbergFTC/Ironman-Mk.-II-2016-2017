package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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
    DcMotor leftfront;
    DcMotor rightfront;
    DcMotor ballhope;
    DcMotor collector;

    protected void Clear()
    {
    }

    public IIOpMode()
    {
        super();
        leftback = null;
        rightback = null;
        shooter = null;
        ballhope = null;
    }

    public void Init()
    {
        //Initializes the configuration for the rightfront = hardwareMap.dcMotor.get("rf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightback = hardwareMap.dcMotor.get("rb");
        shooter = hardwareMap.dcMotor.get("sh");
        leftfront = hardwareMap.dcMotor.get("lf");
        rightfront = hardwareMap.dcMotor.get("rf");
        ballhope = hardwareMap.dcMotor.get("bh");
        collector = hardwareMap.dcMotor.get("cl");

        leftback.setDirection(DcMotor.Direction.REVERSE); //Reverses the left back motor
        leftfront.setDirection(DcMotorSimple.Direction.REVERSE);

        rightback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  //Sets up the robot ready for encoder use
        leftback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ballhope.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void Autonomous_Single_Shot()
    {
        int ShooterTargetValue = shooter.getCurrentPosition();

        shooter.setTargetPosition( 3600 + ShooterTargetValue); //same value as the revoultion of the flipper.

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        shooter.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        shooter.setPower(1);

        //Math.abs(shooter.getCurrentPosition()) < SHOTS_MADE
        while(shooter.isBusy())
        {
            telemetry.addData("Shot Pos", shooter.getCurrentPosition());
            //leftback.setPower(power);
            telemetry.update();
        }
        shooter.setPower(0);

    }
    public  void Autonomous_Shoot()
    {
        int GEAR_1 = 33;
        int GEAR_2 = 64;
        int revolution_of_flipper = (GEAR_2/GEAR_1) * (CLICKS_PER_REVOLUTION);
        int ShooterTargetValue = shooter.getCurrentPosition();

        shooter.setTargetPosition( 3000 + ShooterTargetValue); //same value as the revoultion of the flipper.


        shooter.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        shooter.setPower(1);

        //Math.abs(shooter.getCurrentPosition()) < SHOTS_MADE
        while(shooter.isBusy())
        {
            telemetry.addData("Shot Pos", shooter.getCurrentPosition());
            //leftback.setPower(power);
            telemetry.update();
        }
        shooter.setPower(0);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        collector.setPower(1);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        collector.setPower(0);
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void StopDriving()
    {
        leftback.setPower(0);
        rightback.setPower(0);
    }
    public void Turning(int TurnTicks) throws InterruptedException
    {
        int RightTurnValue = rightback.getCurrentPosition();

        leftback.setTargetPosition(TurnTicks + RightTurnValue);

        leftback.setMode(DcMotor.RunMode.RUN_TO_POSITION); // the encoders are going to this

        leftback.setPower(-1);

        leftback.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(Math.abs(rightback.getCurrentPosition()) < TurnTicks)
        {
            telemetry.addData("Right Turn Value",rightback.getCurrentPosition());
            telemetry.addData("Target", TurnTicks);
            rightback.setPower(0.9);
            rightfront.setPower(0.9);
            leftfront.setPower(-1);
            telemetry.update();
        }
        StopDriving();
    }


    public void TurnRight(double power,int TARGET_TICK) throws InterruptedException
    {
        int RightTurnValue = rightback.getCurrentPosition();

        rightback.setTargetPosition(TARGET_TICK + RightTurnValue);

        rightback.setMode(DcMotor.RunMode.RUN_TO_POSITION); // the encoders are going to this

        rightback.setPower(power);

        while(Math.abs(rightback.getCurrentPosition()) < TARGET_TICK)
        {
            telemetry.addData("Right Turn Value",rightback.getCurrentPosition());
            telemetry.addData("Target", TARGET_TICK);
            leftback.setPower(power * .75);
            telemetry.update();
        }
        StopDriving();

    }
    // leftfront.getCurrentPosition(); //get the current postions of the motors
    // rightfront.getCurrentPosition();
    public void DriveBackwardsDistance(double power, int TARGET_GOAL)
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

                if (results > 0)
                {
                    DriveBackwardsDistance(0.4,500);
                }
                else
                    DriveBackwardsDistance(0.4,700);

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
