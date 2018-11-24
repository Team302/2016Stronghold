package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.ArmSetpoint.ArmState;
import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;


/**
 * This class moves the arm to the Sally port end hook position
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
public class GoToSallyPortEndHookPosition extends Command
{

	/**
	 * I need the arm!
	 */
    public GoToSallyPortEndHookPosition()
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
     * Move to state
     * 
     */
    protected void execute()
    {
        SubsystemFactory.getSubsystemFactory().getArm().setState(ArmState.SALLY_PORT_END_HOOK);
    }

    
    /**
     * Are ye done yet?
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
