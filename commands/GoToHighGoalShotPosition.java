package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team302.robot.subsystems.ArmSetpoint.ArmState;

import edu.wpi.first.wpilibj.command.Command;

public class GoToHighGoalShotPosition extends Command
{

    /**
     * I need an arm
     */
    public GoToHighGoalShotPosition()
    {
        requires(SubsystemFactory.getSubsystemFactory().getArm());
    }

    
    /**
     * Nothing
     * 
     */
    protected void initialize()
    {
        // possibly stop arm???
    }

    
    /**
     * Move the arm
     * 
     */
    protected void execute()
    {
        SubsystemFactory.getSubsystemFactory().getArm().setState(ArmState.HIGH_GOAL_SHOT_POSITION);
    }

    
    /**
     * Are ye done?
     * 
     */
    protected boolean isFinished()
    {
        return SubsystemFactory.getSubsystemFactory().getArm().isAtTargetState(3);
    }

    
    /**
     * Stop the arm
     * 
     */
    protected void end()
    {
        SubsystemFactory.getSubsystemFactory().getArm().stopArm();
    }

    
    /**
     * If you steal the arm I stop running
     * 
     */
    protected void interrupted()
    {
        end();
    }
}
