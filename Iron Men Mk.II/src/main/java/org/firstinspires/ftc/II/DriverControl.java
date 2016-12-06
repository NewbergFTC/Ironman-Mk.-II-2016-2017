package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
    private DcMotor rightfront;
    private DcMotor leftfront;
    private DcMotor shooter;

    //Math for the encoder values
    //final double GEAR_ONE_TEETH= 16;
    //final double WHEEL_CIRCUMFERENCE= 3.875*Math.PI;
    //final double DISTANCE= 25;

    public void init()
    {         //The motors will be called this for the phone configuration
        leftback = hardwareMap.dcMotor.get("lb");
        rightback = hardwareMap.dcMotor.get("rb");
        leftfront = hardwareMap.dcMotor.get("lf");
        rightfront = hardwareMap.dcMotor.get("rf");

        collector = hardwareMap.dcMotor.get("cl");
        shooter = hardwareMap.dcMotor.get("shoot");
        shooter.setDirection((DcMotor.Direction.REVERSE));
        // the wheel is REVERSED
    }

    @Override
    public void loop() {

        double leftpower = -gamepad1.left_stick_y*.75;
        double rightpower = gamepad1.right_stick_y*.75;
        double PowerForward = 1f;
        double PowerBack = -1f;

        double collectorPower = (gamepad1.left_trigger >= 1) ? PowerForward : (gamepad1.right_trigger >= 1) ? PowerBack : 0;
        boolean buttonflipper = gamepad1.a;

        leftfront.setPower(leftpower);
        leftback.setPower(leftpower);
        rightfront.setPower(rightpower);
        rightback.setPower(rightpower);
        collector.setPower(collectorPower);

        if(buttonflipper)
        {
          shooter.setPower(1);
        }
        else
        {
         shooter.setPower(0);
        }
        /*
        float turningPower = gamepad1.left_stick_x;  //Gets value for the left stick y from the controller
        float forward= 0;

        if (gamepad1.right_trigger > 0) //using the right trigger on gamepad.1 to accelerate
        {
            forward = gamepad1.right_trigger;
        }
        else if (gamepad1.left_trigger > 0) //using the left trigger to go backwards
        {
            forward = -gamepad1.left_trigger;
        }

        leftback.setPower(forward); //sets the power to the two motors. Gets the forward power from the if statement above
        rightback.setPower(forward);



        if (forward == 0 ) //only turning in place

        {
            if (turningPower < 0)
            {
                //Turn left
                leftback.setPower(turningPower);
                rightback.setPower(-turningPower);
            }
            else if (turningPower > 0)
            {
                leftback.setPower(-turningPower);
                rightback.setPower(turningPower);
            }
        }
        else //this is moving forward and turning
        {
            if (turningPower < 0)
            {
            //Turn left
            leftback.setPower(-turningPower*.5);//slow down the left wheels to turn left more slowly
            rightback.setPower(-turningPower);
            }
            else if (turningPower > 0)
            {
            leftback.setPower(turningPower); //slow down the right wheels to turn right more slowly
            rightback.setPower(turningPower*.5);
            }
        */
        }

        //the float and the collector power will enable the collector to move forward and backwards
}