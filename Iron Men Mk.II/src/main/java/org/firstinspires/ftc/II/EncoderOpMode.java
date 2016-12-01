package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Marcos on 11/19/2016.
 */

 abstract public class EncoderOpMode extends LinearOpMode {

     private DcMotor leftback = null;
     private DcMotor rightback = null;

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

    // leftfront.getCurrentPosition(); //get the current postions of the motors
    // rightfront.getCurrentPosition();


    public void DriveForwardDistance(double power, int distance)
    {
        leftback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightback.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

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
}
