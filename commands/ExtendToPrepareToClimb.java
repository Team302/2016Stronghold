package org.usfirst.frc.team302.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExtendToPrepareToClimb extends CommandGroup
{

    public ExtendToPrepareToClimb()
    {
        addSequential(new GoToStartingPosition());
        addSequential(new GoToExtendedToPrepareToClimbPosition());
    }
}
