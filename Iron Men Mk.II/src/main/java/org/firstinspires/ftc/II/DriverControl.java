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
    private DcMotor leftback;;
    private DcMotor rightback;
    private DcMotor collector;
    private DcMotor shooter;

    //Math for the encoder values
    //final double GEAR_ONE_TEETH= 16;
    //final double WHEEL_CIRCUMFERENCE= 3.875*Math.PI;
    //final double DISTANCE= 25;

    public void init()
    {         //The motors will be called this for the phone configuration
        leftback = hardwareMap.dcMotor.get("lb");
        rightback = hardwareMap.dcMotor.get("rb");
        collector = hardwareMap.dcMotor.get("cl");
        shooter = hardwareMap.dcMotor.get("shoot");
        rightback.setDirection(DcMotor.Direction.REVERSE);
        shooter.setDirection((DcMotor.Direction.REVERSE));
        // the wheel is REVERSED
    }

    @Override
    public void loop() {
         //Gets value for the left stick y from the controller
        float turningpower = gamepad1.left_stick_x;
        float forward= 0;

        /*
        //values for the power for each wheel---These values can be played around with
        double backleftpower;
        double backrightpower;
        double frontleftpower;
        double frontrightpower;
        */

        if (gamepad1.right_trigger > 0) //using the right trigger on gamepad.1 to accelerate
        {
            forward = 1;
        }
        else if (gamepad1.left_trigger > 0) //using the left trigger to go backwards
        {
            forward = -1;
        }

        leftback.setPower(forward); //sets the power to the two motors. Gets the forward power from the if statement above
        rightback.setPower(forward);



        if (forward == 0 ) //only turning in place

        {
            if (turningpower < 0)
            {
                //Turn left
                leftback.setPower(turningpower);
                rightback.setPower(-turningpower);
            }
            else if (turningpower > 0)
            {
                leftback.setPower(-turningpower);
                rightback.setPower(turningpower);
            }
        }
        else //this is moving forward and turning
        {
            if (turningpower < 0)
            {
            //Turn left
            leftback.setPower(-turningpower*.5*forward);//slow down the left wheels to turn left more slowly
            rightback.setPower(-turningpower*forward);
            }
            else if (turningpower > 0)
            {
            leftback.setPower(turningpower*forward); //slow down the right wheels to turn right more slowly
            rightback.setPower(turningpower*.5*forward);
            }

        }

        //the float and the collector power will enable the collector to move forward and backwards

        float PowerForward = (float) 1;
        float PowerBack = (float) -1;
        float collectorpower = (gamepad2.left_trigger >= 1) ? PowerForward : (gamepad2.right_trigger >= 1) ? PowerBack : 0;
        float flipperPower = 1f;
        float reversepowerflipper = -1f;

        boolean buttonflipper = gamepad2.a;
        boolean reverseflipper = gamepad2.b;


        if (buttonflipper) {    //if the gamepad a button is pressed it will set the power to the motor
            shooter.setPower(flipperPower);
        }
        else {
            shooter.setPower(0); //if the a button is not being it will
        }

        if(reverseflipper) {
            shooter.setPower(reversepowerflipper);
        }
        else{
            shooter.setPower(0);
        }
        collector.setPower(collectorpower);   //Sets the power for the collector

    }
}