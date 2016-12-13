package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Marcos on 12/7/2016.
 */
@Autonomous (name = "Red Autonomous")

public class RedAutonomousMode extends IIOpMode {

    @Override
    public void Init()
    {
        super.Init();
    }

    public void Run() throws InterruptedException
    {
        DriveForwardDistance(0.5,5520);

        TurnLeft(0.5);

        DriveForwardDistance(0.5,920);

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


