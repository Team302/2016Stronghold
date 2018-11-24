package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.ArmSetpoint.ArmState;
import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;


/**
 * This class moves the arm to the portcullis start position
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
public class GoToPrePortcullisStartPosition extends Command
{

	
	/**
	 * I need the arm!
	 */
    public GoToPrePortcullisStartPosition()
    {
        requires(SubsystemFactory.getSubsystemFactory().getArm());
//        setTimeout(5.0);
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
     * Move to state
     * 
     */
    protected void execute()
    {
        SubsystemFactory.getSubsystemFactory().getArm().setState(ArmState.P_PORTCULLIS_POSITION);
    }

    
    /**
     * Are ye done?
     * 
     */
    protected boolean isFinished()
    {
        return SubsystemFactory.getSubsystemFactory().getArm().isAtTargetState(4.5);//TODO might have to change to 4 degrees

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
