package org.firstinspires.ftc.II;

/**
 * Created by Marcos on 12/13/2016.
 */

public abstract class  TimeBasedAutonomous extends IIOpMode {

    public void Init()
    {
        super.Init();
    }

    public void Run() throws InterruptedException
    {
        leftback.setPower(0.5);
        rightback.setPower(0.5);
        wait();
    }
}
