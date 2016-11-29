package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Marcos on 10/4/2016.
 */
@TeleOp(name = "Driver Control")
public class DriverControl extends OpMode {

    //Initializes the motors
    private DcMotor leftfront;
    private DcMotor leftback;
    private DcMotor rightfront;
    private DcMotor rightback;
    private DcMotor collector;
    private DcMotor shooter;

    //Math for the encoder values
    //final double GEAR_ONE_TEETH= 16;
    //final double GEAR_TWO_TEETH= 32 ;
    //final double WHEEL_CIRCUMFERENCE= 3.875*Math.PI;
    //final double DISTANCE= 25;

    public void init() {         //The motors will be called this for the phone configuration
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");
        collector = hardwareMap.dcMotor.get("cl");
        shooter = hardwareMap.dcMotor.get("shoot");
        //rightfront.setDirection(DcMotor.Direction.REVERSE);
        shooter.setDirection((DcMotor.Direction.REVERSE));
        // the wheel is REVERSED
    }

    @Override
    public void loop() {
        float forwardpower = gamepad1.left_stick_y;    //Gets value for the left stick y from the controller
        float RightY = gamepad1.right_stick_y;
        float turningpower = gamepad1.left_stick_x;
        float RightX = gamepad1.right_stick_x;
        //Gets values for the y from the controller

        //values for the power for each wheel---These values can be played around with
        double backleftpower;
        double backrightpower;
        double frontleftpower;
        double frontrightpower;


        if(turningpower < -.3)
        {
            //Turn left
            leftfront.setPower(turningpower);
            leftback.setPower(turningpower);
            rightfront.setPower(-turningpower);
            rightback.setPower(-turningpower);
        }
        else if(turningpower > .3 )
        {
            leftfront.setPower(-turningpower);
            leftback.setPower(-turningpower);
            rightfront.setPower(turningpower);
            rightback.setPower(turningpower);
        }

            leftfront.setPower(forwardpower);
            leftback.setPower(forwardpower);
            rightfront.setPower(forwardpower);
            rightback.setPower(forwardpower);


        //the float and the collector power will enable the collector to move forward and backwards

        float PowerForward = (float) 1;
        float PowerBack = (float) 1;
        float collectorpower = (gamepad1.left_trigger >= 1) ? PowerForward : (gamepad1.right_trigger >= 1) ? PowerBack : 0;
        float flipperPower = 1f;
        float reversepowerflipper = -1f;

        boolean buttonflipper = gamepad1.a;
        boolean reverseflipper =gamepad1.b;

        if (buttonflipper) {
            shooter.setPower(flipperPower);
        }
        else {
            shooter.setPower(0);
        }

        if(reverseflipper) {
            shooter.setPower(reversepowerflipper);
        }
        else{
            shooter.setPower(0);
        }

        //values for the power for each wheel---These values can be played around with


        //Sets the power for the collector
        collector.setPower(collectorpower);

        //the code for the motor for the collector
    }
}