package org.usfirst.frc.team302.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PrePortcullisPositionGroup extends CommandGroup
{
    public PrePortcullisPositionGroup()
    {
        addSequential(new GoToUnderLowBarPosition());
        addSequential(new GoToPrePortcullisStartPosition());
    }
}
