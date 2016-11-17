package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Marcos on 10/19/2016.
 */
@Autonomous(name = "Encoder Test")
public abstract class EncoderTest extends LinearOpMode {

    private DcMotor leftfront;
    private DcMotor leftback;
    private DcMotor rightfront;
    private DcMotor rightback;
    double nopower = 0;
    double halfpower = 0;


    final double WHEEL_CIRCUMFERENCE = 3.875 * Math.PI;
    final double DISTANCE = 24;
    final double COUNTS_PER_REVOLUTION = 1120;
    double INCHES = DISTANCE;
    double goal = (COUNTS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE) * INCHES;
    int distance = 2208;
    //the .setTargetPosition()reads the amount of clicks the encoders goes by. I used the top equation to get this value

    public void runOpMode() { //Initializes the configuration for the phones
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");

        rightfront.setDirection(DcMotor.Direction.REVERSE); //Reverses the motors
        rightback.setDirection(DcMotor.Direction.REVERSE);

        rightfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  //Sets up the robot ready for encoders use
        leftfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftfront.getCurrentPosition();
        rightfront.getCurrentPosition();

    while (opModeIsActive())

        //waitForStart(); //wait fot the start of automonous; pressing the button.

        leftfront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightfront.setMode(DcMotor.RunMode.RESET_ENCODERS);

        rightfront.setTargetPosition(distance); //sets the Target position for the motors
        leftfront.setTargetPosition(distance);

        rightfront.setMode(DcMotor.RunMode.RUN_TO_POSITION); // the encoders are going to this postion
        leftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftfront.setPower(halfpower);         //sets the power to the Target value. In this case its 0.5
        rightfront.setPower(halfpower);

        while (leftfront.isBusy() && rightfront.isBusy()) {  //while the robot is going to the postion the encoders wont get any info

        }
        leftfront.setPower(nopower); // sets the motors with no power
        rightfront.setPower(nopower);
    }
}
