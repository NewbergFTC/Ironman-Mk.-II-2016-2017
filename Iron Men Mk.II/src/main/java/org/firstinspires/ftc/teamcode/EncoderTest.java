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

    //final double GEAR_ONE_TEETH= 16;
    //final double GEAR_TWO_TEETH= 32 ;
    final double WHEEL_CIRCUMFERENCE= 3.875*Math.PI;
    //final double DISTANCE= 25;
    final double COUNTS_PER_REVOLUTION= 1120;

    public void runOpMode() throws InterruptedException {
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");

        rightfront.setDirection(DcMotor.Direction.REVERSE);
        rightback.setDirection(DcMotor.Direction.REVERSE);

        rightfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        int ticks = rightfront.getCurrentPosition();
    }

    @Override
    public synchronized void waitForStart() throws InterruptedException {
        waitForStart();
        while(opModeIsActive()) {
            //double INCHES = DISTANCE
            double goal= (GEAR_ONE_TEETH)*(COUNTS_PER_REVOLUTION/WHEEL_CIRCUMFERENCE)*INCHES;

            _Rightcon.set


        }
    }
}


