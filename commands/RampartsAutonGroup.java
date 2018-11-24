package org.usfirst.frc.team302.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RampartsAutonGroup extends CommandGroup
{
    public RampartsAutonGroup()
    {
        addParallel(new CrossObstacle("ramparts"));
        addParallel(new GoToUnderLowBarPosition());
    }
}
