package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Marcos on 10/19/2016.
 */
@Autonomous(name = "Blue Autonomous")

public class BlueAutonomousMode extends IIOpMode

{
    @Override
    public void Init()
    {
        super.Init();
    }

    public void Run() throws InterruptedException

    {
        Turning(20);

        DriveBackwardsDistance(.6, 3600); // Power is set first then is the target in ticks

        Autonomous_Shoot(); //This mode shoots a ball and moves the collector which will position a ball

        Autonomous_Single_Shot(); //This only shoots the ball

        Turning(80);

        getBlueRightSide();

    }
}



