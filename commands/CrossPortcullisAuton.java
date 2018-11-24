package org.usfirst.frc.team302.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CrossPortcullisAuton extends CommandGroup
{
    public CrossPortcullisAuton() //TODO Zesty Testy Test
    {
        addSequential(new PrePortcullisPositionGroup());
        addSequential(new CrossObstacle("roughterrain"));
    }
}
