package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Marcos on 10/4/2016.
 */
@TeleOp(name = "Driver Control 2")
public class DriverControl2 extends OpMode {

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
        float rightX = gamepad1.left_stick_x;    //Gets value for the x from the controller
        float leftY = -gamepad1.right_stick_y; //Gets values for the y from the controller

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
        double backleftpower = (leftY);
        double backrightpower = (rightX);
        double frontleftpower = (leftY);
        double frontrightpower = (rightX);

        //Sets the power for each of the wheels
        leftfront.setPower(frontleftpower);
        rightfront.setPower(frontrightpower);
        leftback.setPower(backleftpower);
        rightback.setPower(backrightpower);
        collector.setPower(collectorpower);

        //the code for the motor for the collector
    }
}