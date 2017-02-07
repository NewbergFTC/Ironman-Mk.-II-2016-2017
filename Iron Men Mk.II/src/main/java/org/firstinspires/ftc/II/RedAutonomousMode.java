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
        DriveBackwardsDistance(-0.5,5520);

        Autonomous_Shoot();

        Autonomous_Single_Shot();

        //Turning(-100);

        //getBlueRightSide();


    }
}


