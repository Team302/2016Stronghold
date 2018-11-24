package org.usfirst.frc.team302.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveOverMoatDistanceBased extends CommandGroup
{

    public DriveOverMoatDistanceBased()
    {
        addSequential(new DriveToDistanceWithSingleEncoder(70.0, 0, 0, 1.0));
        addSequential(new DriveToDistanceWithSingleEncoder(105.0, 0, 0, 0.7));
    }
}
