package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
/**
 * Created by Marcos on 10/4/2016.
 */
@TeleOp(name = "Testing Drive")
public class DriverControl extends OpMode{

    private DcMotor leftfront;
    private DcMotor leftback;
    private DcMotor rightfront;
    private DcMotor rightback;
    //final double GEAR_ONE_TEETH= 16;
    //final double GEAR_TWO_TEETH= 32 ;
    //final double WHEEL_CIRCUMFERENCE= 3.875*Math.PI;
    //final double DISTANCE= 25;

    public void init(){
        leftfront = hardwareMap.dcMotor.get("lf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightfront = hardwareMap.dcMotor.get("rf");
        rightback = hardwareMap.dcMotor.get("rb");
        rightfront.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        float leftY = gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;
        double backleftpower = (leftY) * .75;
        double backrightpower = (rightY) * .75;
        double frontleftpower = (leftY * .9 * .75 * -1);
        double frontrightpower = (rightY * .9) * .75;
        leftfront.setPower(frontleftpower);
        rightfront.setPower(frontrightpower);
        leftback.setPower(backleftpower);
        rightback.setPower(backrightpower);



        rightfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int ticks = rightfront.getCurrentPosition();

    }
}