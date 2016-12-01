package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Marcos on 10/19/2016.
 */
@Autonomous(name = "Encoder Test")

public abstract class EncoderTest extends EncoderOpMode {

    private DcMotor leftback;
    private DcMotor rightback;

    //the .setTargetPosition()reads the amount of clicks the encoders goes by. I used the top equation to get this value

    public void Init()
    {
        //Initializes the configuration for the         rightfront = hardwareMap.dcMotor.get("rf");
        leftback = hardwareMap.dcMotor.get("lb");
        rightback = hardwareMap.dcMotor.get("rb");


        rightback.setDirection(DcMotor.Direction.REVERSE); //Reverses the right back motors

        rightback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  //Sets up the robot ready for encoder use
        leftback.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void runOpMode() throws InterruptedException
    {
        Init();

        waitForStart();

        DriveForwardDistance(0.5,10000);

        TurnRight(0.5);
    }
}



