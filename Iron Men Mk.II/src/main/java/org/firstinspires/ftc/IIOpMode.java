package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.lasarobotics.vision.android.Cameras;
import org.lasarobotics.vision.ftc.resq.Beacon;
import org.lasarobotics.vision.opmode.LinearVisionOpMode;
import org.lasarobotics.vision.opmode.extensions.CameraControlExtension;
import org.lasarobotics.vision.util.ScreenOrientation;
import org.opencv.core.Size;

/**
 * Created by Marcos on 11/16/2016.
 */

public abstract class IIOpMode extends LinearVisionOpMode {

    DcMotor leftback;
    DcMotor rightback;

    protected void Clear()
    {
    }

    public IIOpMode()
    {
        super();
        leftback = null;
        rightback = null;
    }

    public void Init()
    {
        //Initializes the configuration for the rightfront = hardwareMap.dcMotor.get("rf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightback = hardwareMap.dcMotor.get("rb");


        rightback.setDirection(DcMotor.Direction.REVERSE); //Reverses the right back motors

        rightback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  //Sets up the robot ready for encoder use
        leftback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void DriveForward(double power)
    {
        leftback.setPower(power);
        rightback.setPower(power);
    }
    public void StopDriving()
    {
        DriveForward(0);
    }


    public void TurnLeft(double power) throws InterruptedException
    {
        leftback.setPower(-power);
        rightback.setPower(power);
        wait(150);
        leftback.setPower(0);
        rightback.setPower(0);
    }
    public void TurnRight(double power) throws InterruptedException
    {
        TurnLeft(-power);
    }
    public  void Reverse (double power) throws InterruptedException
    {
        leftback.setPower(-power);
        rightback.setPower(power);
        wait(500);
        leftback.setPower(0);
        rightback.setPower(0);
    }
    // leftfront.getCurrentPosition(); //get the current postions of the motors
    // rightfront.getCurrentPosition();


    public void DriveForwardDistance(double power, int distance)
    {
        rightback.setTargetPosition(distance); //sets the Target position for the motors
        leftback.setTargetPosition(distance);

        rightback.setMode(DcMotor.RunMode.RUN_TO_POSITION); // the encoders are going to this postion
        leftback.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftback.setPower(power);         //sets the power to the Target value.
        rightback.setPower(power);

        while (leftback.isBusy() && rightback.isBusy())
        {
            //while the robot is going to the postion the encoders wont get any info
        }
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
