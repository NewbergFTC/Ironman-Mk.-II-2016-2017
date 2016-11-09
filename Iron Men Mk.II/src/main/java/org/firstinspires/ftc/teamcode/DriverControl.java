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
public class DriverControl extends OpMode{

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

    public void init(){         //The motors will be called this for the phone configuration
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

        //values for the power for each wheel---These values can be played around with
        double backleftpower = (rightY);
        double backrightpower = (rightY);
        double frontleftpower = (leftY);
        double frontrightpower = (leftY);
        double collectorpower = 1;
        double nopower= 0;

        //Sets the power for each of the wheels
        leftfront.setPower(frontleftpower);
        rightfront.setPower(frontrightpower);
        leftback.setPower(backleftpower);
        rightback.setPower(backrightpower);

        //the code for the motor for the collector
        if (gamepad1.a) {           //detects if a is being pressed
            collector.setPower(collectorpower);  //sets power to 1
        }
        collector.setPower(nopower);      //if the gamepad1.a is not pressed it sets the power to zero
    }
}