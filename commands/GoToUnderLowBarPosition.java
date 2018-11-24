package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team302.robot.subsystems.ArmSetpoint.ArmState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GoToUnderLowBarPosition extends Command
{

    /**
     * I need an arm
     */
    public GoToUnderLowBarPosition()
    {
        requires(SubsystemFactory.getSubsystemFactory().getArm());
    }

    
    /**
     * Nothing
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
        SubsystemFactory.getSubsystemFactory().getArm().setState(ArmState.UNDER_LOW_BAR_POSITION);
    }

    
    /**
     * Are ye done?
     * 
     */
    protected boolean isFinished()
    {
        SmartDashboard.putBoolean("Low bar done", SubsystemFactory.getSubsystemFactory().getArm().isAtTargetState(4));
        return SubsystemFactory.getSubsystemFactory().getArm().isAtTargetState(4.5);
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
