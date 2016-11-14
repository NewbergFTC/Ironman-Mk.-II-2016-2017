package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
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
        rightfront.setDirection(DcMotor.Direction.REVERSE);
        // the wheel is REVERSED
    }

    @Override
    public void loop() {
        float leftY = gamepad1.left_stick_y;    //Gets value for the x from tje controller
        float rightY = -gamepad1.right_stick_y; //Gets values for the y from the controller

        //the float and the collector power will enable the collector to move forward and backwards
        float PowerForward = (float) .1;
        float PowerBack = (float) -.1;
        float collectorpower = (gamepad1.left_trigger >= 0.01) ? PowerForward : (gamepad1.right_trigger >= 0.01) ? PowerBack : 0;

        //values for the power for each wheel---These values can be played around with
        double backleftpower = (leftY);
        double backrightpower = (rightY);
        double frontleftpower = (leftY);
        double frontrightpower = (rightY);

        PowerForward = (gamepad2.a) ? -1 : (float) -0.01;
        PowerBack = (gamepad2.a) ? 1 : (float) 0.01;


        //Sets the power for each of the wheels
        leftfront.setPower(frontleftpower);
        rightfront.setPower(frontrightpower);
        leftback.setPower(backleftpower);
        rightback.setPower(backrightpower);
        collector.setPower(collectorpower);

        //the code for the motor for the collector
    }
}