package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by Marcos on 1/28/2017.
 */

@Autonomous (name = "Blue Autonomous Further Distance")
public class BlueAutonomousFurtherDistance extends IIOpMode {

    @Override
    public void Init()
    {
        super.Init();
    }

    public void Run() throws InterruptedException

    {
        DriveBackwardsDistance(.6,2700); // Power is set first then is the target in ticks

        Turning(-10);

        Autonomous_Shoot();

        Autonomous_Single_Shot();

        //Turning(100);

        //getBlueRightSide();
    }
}
