package org.usfirst.frc.team302.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PortcullisStart extends CommandGroup
{
    public PortcullisStart()
    {
        addSequential(new GoToUnderLowBarPosition());
        addSequential(new GoToPortcullisStartPosition());
    }   
}