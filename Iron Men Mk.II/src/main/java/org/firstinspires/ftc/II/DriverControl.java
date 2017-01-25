package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/*
 * Created by Marcos on 10/4/2016.
 */
@TeleOp(name = "Driver Control")
public class DriverControl extends OpMode {

    //Calls the motor to be used later on in the code. Right now we have 6 motors used.
    private DcMotor leftback;
    private DcMotor rightback;
    private DcMotor collector;
    private DcMotor rightfront;
    private DcMotor leftfront;
    private DcMotor shooter;
    private Servo ballHolder1;
    private Servo ballHolder2;
    double pushing = 0.35;

    public void init() //The motors will be initialized in this section of the code
    {
        leftback = hardwareMap.dcMotor.get("lb"); //Here the hardware is being mapped out by the code. This is also were you determine what names you will enter to the phone configuration to call that motor
        rightback = hardwareMap.dcMotor.get("rb");// The right back motor is called "rb" in the phone configuration. To call the right back motor enter "rb" to whichever motor controller and motor port on the motor controller the motor is connected
        leftfront = hardwareMap.dcMotor.get("lf");
        rightfront = hardwareMap.dcMotor.get("rf");
        ballHolder1 = hardwareMap.servo.get("bh1");
        ballHolder2 = hardwareMap.servo.get("bh2");


        collector = hardwareMap.dcMotor.get("cl");
        shooter = hardwareMap.dcMotor.get("sh");
        shooter.setDirection((DcMotor.Direction.REVERSE)); // This line of code reverses the motor direction. You would use this if you want the motor to be the same as the others in direction.
        // the motor is REVERSED as we need to collector to spin to the right not to the left
    }

    @Override
    public void loop()
    { //The robot is going to run this segment of code over and over allowing something to be 1 for a while and a zero at another time

        //The code is setting power values for the motors on the wheels
        double leftpower = -gamepad1.left_stick_y; //the left two motors are getting a negative value to go the same direction as the right motors
        double rightpower = gamepad1.right_stick_y; // Testing our code some of our wheels were jerky and choppy. We fixed this by  multiplying 75% of the value the controller gives. This slows down the wheels by 25%

        //The collector is using the left and right triggers to set the power to the collector motor
        double PowerForward = 1f; //This is the power if the motor is needed to go forward
        double PowerBack = -1f; //This is the power if the motor is needed to go backwards
        double collectorpower = (gamepad1.left_trigger >= 1) ? PowerForward : (gamepad1.right_trigger >= 1) ? PowerBack : 0; //This line of code determines what trigger is being pressed.  Left or right and setting the collector power to whichever button is being pressed

        boolean flipperB = gamepad2.b; //A boolean is a true and false. The game pad has most functions as booleans.

        //This fragment of code sets the power to the motors from previous lines of code
        leftfront.setPower(leftpower); //The "leftpower" from above is used here to set the power to the left front wheel
        leftback.setPower(leftpower); //The "leftpower" from above is used here to set the power to the left back wheel
        rightfront.setPower(rightpower); //The "rightpower" from above is used here to set the power to the right front wheel
        rightback.setPower(rightpower); //The "rightpower" from above is used here to set the power to the right back wheel
        collector.setPower(collectorpower); //The "collectorpower" is  either 1 or -1. The code from above determines this. The collector motor is given a value here

        if(gamepad2.a)
        {
            ballHolder1.setPosition(1);
        }
        else
        {
            ballHolder1.setPosition(pushing);
        }

        if(flipperB)

            shooter.setPower(PowerBack);
        else
        {
            shooter.setPower(0);
        }

        /*
        //this is some variation of the drive code. Our driver enjoyed the tank drive more then an a video game influenced driving style
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