package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Marcos on 12/7/2016.
 */
@Autonomous (name = "Red Autonomous")

public class RedAutonomousMode extends IIOpMode {

    public void Run() throws InterruptedException
    {
        Init();

        waitForStart();

        DriveForwardDistance(-0.5,920);

        TurnLeft(0.5);

        getBlueRightSide();
    }
}


