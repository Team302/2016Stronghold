package org.usfirst.frc.team302.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossRampartsAuton extends CommandGroup
{
    public CrossRampartsAuton()
    {
        addSequential(new DriveToDistanceWithSingleEncoder(250.0, 0.0, 0.0, 1.0));
    }
}
