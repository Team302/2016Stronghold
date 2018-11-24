package org.usfirst.frc.team302.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbGroup extends CommandGroup
{
    public ClimbGroup()
    {
        addSequential(new GoToClimbHookPosition());
        addSequential(new GoToClimbEndPosition());
    }
}
