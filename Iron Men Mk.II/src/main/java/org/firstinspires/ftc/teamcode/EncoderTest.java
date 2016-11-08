package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Marcos on 10/19/2016.
 */
@Autonomous(name = "Encoder Test")
abstract public class EncoderTest extends LinearOpMode {

    private DcMotor leftfront;
    private DcMotor leftback;
    private DcMotor rightfront;
    private DcMotor rightback;
    private DcMotorController _Leftcon;
    private DcMotorController _Rightcon;


    final double WHEEL_CIRCUMFERENCE= 3.875*Math.PI;
    final double DISTANCE= 24;
    final double COUNTS_PER_REVOLUTION= 1120;
    double INCHES= DISTANCE;
    double goal = (COUNTS_PER_REVOLUTION/WHEEL_CIRCUMFERENCE)*INCHES;
    int distance= 2208;
    //the .setTargetPosition()reads the amount of clicks the encoders goes by. I used the top equation to get this value

    public void runOpMode() throws InterruptedException {
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");

        rightfront.setDirection(DcMotor.Direction.REVERSE);
        rightback.setDirection(DcMotor.Direction.REVERSE);

        rightfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftfront.getCurrentPosition();
        rightfront.getCurrentPosition();


    }

    @Override
    public synchronized void waitForStart() throws InterruptedException {
        waitForStart();


        leftfront.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rightfront.setMode(DcMotor.RunMode.RESET_ENCODERS);

        rightfront.setTargetPosition(distance);
        leftfront.setTargetPosition(distance);

        rightfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftfront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftfront.setPower(0.5);
        rightfront.setPower(0.5);

        while (leftfront.isBusy() && rightfront.isBusy()) {

        }
        leftfront.setPower(0);
        rightfront.setPower(0);
    }
}


