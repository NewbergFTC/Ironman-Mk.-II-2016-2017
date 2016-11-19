package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Marcos on 10/19/2016.
 */
@Autonomous(name = "Encoder Test")

public abstract class EncoderTest extends EncoderOpMode {

    private DcMotor leftfront= null;
    private DcMotor rightfront = null;

    //the .setTargetPosition()reads the amount of clicks the encoders goes by. I used the top equation to get this value

    public void Init()
    {
        //Initializes the configuration for the         rightfront = hardwareMap.dcMotor.get("rf");
        leftfront = hardwareMap.dcMotor.get("lf");
        rightfront = hardwareMap.dcMotor.get("rf");


        rightfront.setDirection(DcMotor.Direction.REVERSE); //Reverses the motors

        rightfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  //Sets up the robot ready for encoder use
        leftfront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void runOpMode() throws InterruptedException
    {
        Init();

        waitForStart();



        DriveForwardDistance(0.5,2600);
    }



}



