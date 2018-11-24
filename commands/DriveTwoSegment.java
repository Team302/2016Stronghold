package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.Drive.autonAngles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveTwoSegment extends CommandGroup
{

    public DriveTwoSegment()
    {
        //addSequential(new AimNeutral());
        //addSequential(new GoToUnderLowBarPosition());
        
        addSequential(new DriveToDistanceWithSingleEncoder(70.0, 0, 0, 0.7));
        addSequential(new DriveToDistanceWithSingleEncoder(105.0, 0, 0, 0.7));
//        addParallel(new AimHighGoal());
//        addParallel(new GoToHighGoalShotPosition());
        
//        addSequential(new anglesButtons(autonAngles.FIRSTPOSITION));
        
//        addSequential(new DriveToDistanceWithSingleEncoder(-15.0, 0.0, 0.0, 0.5));
        
//        addParallel(new AimHighGoal());
//        addParallel(new AutonShootBoulder());
        
//        addSequential(new CenterTargetInCamera());
//        addParallel(new AimShooterAtTarget());
//        addParallel(new AutonShootBoulder());
//        addSequential(new AutonShootBoulderGroup());
    }
}
