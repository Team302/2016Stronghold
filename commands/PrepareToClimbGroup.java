package org.usfirst.frc.team302.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PrepareToClimbGroup extends CommandGroup
{
    public PrepareToClimbGroup()
    {
        addSequential(new GoToExtendedToPrepareToClimbPosition());
    }
}
