package org.firstinspires.ftc.II;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.lasarobotics.vision.ftc.resq.Beacon;


/**
 * Created by Marcos on 11/28/2016.
 */
@Autonomous (name= "BlueAutonomous")
public class BlueAutonomousMode extends IIOpMode
{
    public int getRedSide() throws InterruptedException
    {
        int results = 0;
        long TimeSlept = 0;

        while(opModeIsActive())
        {
            if(hasNewFrame())
            {
                Beacon.BeaconAnalysis beaconAnalysis = beacon.getAnalysis();

                results = beaconAnalysis.isRightRed() ? 1 : 0;

                break;
            }
            else
            {
                if(TimeSlept > 10000)
                {
                    results = -1;

                    break;
                }

                TimeSlept += 10;
                sleep(10);
            }

            Update();
        }

        return results;
    }
}
