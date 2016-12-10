package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Marcos on 10/19/2016.
 */
@Autonomous(name = "Blue Autonomous")

public class BlueAutonomousMode extends IIOpMode {

    public void Run() throws InterruptedException
    {
        Init();

        waitForStart();

        DriveForwardDistance(0.5,5520);

        TurnRight(0.5);

        DriveForwardDistance(0.5,920);

        getBlueRightSide();

    }
}



