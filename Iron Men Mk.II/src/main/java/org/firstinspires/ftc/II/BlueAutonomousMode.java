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
        //DriveForwardDistance(.6,4200); // Power is set first then is the target in ticks

        //Autonomous_Shoot(1);

        Turning(1);

        getBlueRightSide();
    }
    /* This is another way of calling the autonomous program. If the top one does not work.
    waitForStart();

    while (opModeIsActive())
    {


    DriveForwardDistance(0.5,5520);

    TurnLeft(0.5);

    DriveForwardDistance(0.5,920);

    getBlueRightSide();
    }
    */
}



