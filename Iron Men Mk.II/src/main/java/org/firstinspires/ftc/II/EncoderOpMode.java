package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Marcos on 11/19/2016.
 */

 abstract public class EncoderOpMode extends LinearOpMode {

    public DcMotor leftfront;
    public DcMotor rightfront;


    public void DriveForward(double power)
    {
        leftfront.setPower(power);
        rightfront.setPower(power);
    }
    public void StopDriving()
    {
        DriveForward(0);
    }
    public void TurnLeft(double power)
    {
        leftfront.setPower(-power);
        rightfront.setPower(power);
    }
    public void TurnRight(double power)
    {
        TurnLeft(-power);
    }

    // leftfront.getCurrentPosition(); //get the current postions of the motors
    // rightfront.getCurrentPosition();


    public void DriveForwardDistance(double power, int distance)
    {
        leftfront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightfront.setMode(DcMotor.RunMode.RESET_ENCODERS);

        rightfront.setTargetPosition(distance); //sets the Target position for the motors
        leftfront.setTargetPosition(distance);

        rightfront.setMode(DcMotor.RunMode.RUN_TO_POSITION); // the encoders are going to this postion
        leftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftfront.setPower(power);         //sets the power to the Target value.
        rightfront.setPower(power);

        while (leftfront.isBusy() && rightfront.isBusy())
        {
            //while the robot is going to the postion the encoders wont get any info
        }
        StopDriving();

    }
}
