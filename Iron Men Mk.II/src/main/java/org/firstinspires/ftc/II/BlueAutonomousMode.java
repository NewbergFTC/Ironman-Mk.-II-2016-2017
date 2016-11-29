package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.lasarobotics.vision.ftc.resq.Beacon;


/**
 * Created by Marcos on 11/28/2016.
 */
@Autonomous (name= "BlueAutonomous")
public class BlueAutonomousMode extends IIOpMode
{

    private int BlueRightSide() throws InterruptedException
    {
        int results = 0;
        long Timeslept = 0;

        while(opModeIsActive())
        {
            if(hasNewFrame())
            {
                Beacon.BeaconAnalysis beaconAnalysis = beacon.getAnalysis();

                results = beaconAnalysis.isRightBlue() ? 1 : 0;

                break;
            }
            else
            {
                if(Timeslept > 10000)
                {
                    results = -1;

                    break;
                }

                Timeslept += 10;
                sleep(10);
            }

            Update();
        }

        return results;
    }
}
