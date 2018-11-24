package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.ArmSetpoint.ArmState;
import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;


/**
 * This class moves the arm to the starting position
 * 
 * <h1>Change Log: </h1>
 * 
 * @version <b>version 1:</b>
 * 			<p>
 * 				3/5/2016 --Derek Witcpalek -- initial documentation
 * 			</p>
 * 
 * @author Derek
 *
 */
public class GoToStartingPosition extends Command
{

	/**
	 * I need the arm!
	 */
    public GoToStartingPosition()
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
     * Move to setpoint
     * 
     */
    protected void execute()
    {
        SubsystemFactory.getSubsystemFactory().getArm().setState(ArmState.STARTING_POSITION);
    }

    
    /**
     * Are ye done yet?
     * 
     */
    protected boolean isFinished()
    {
        return SubsystemFactory.getSubsystemFactory().getArm().isAtTargetState(1);
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
