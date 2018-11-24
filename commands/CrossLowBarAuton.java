package org.usfirst.frc.team302.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossLowBarAuton extends CommandGroup
{
    public CrossLowBarAuton()
    {
        //addSequential(new AimNeutral());
        addSequential(new GoToUnderLowBarPosition());
        addSequential(new CrossObstacle("lowbar"));
    }
}
