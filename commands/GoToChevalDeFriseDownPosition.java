package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.ArmSetpoint.ArmState;
import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;


/**
 * This class moves the arm to the cheval de frise down position
 * 
 * <h1>Change Log: </h1>
 * 
 * @version <b>version 1:</b>
 * 			<p>
 * 				3/5/2016 --Derek Witcpalek -- Initial documentation
 * 			</p>
 * 
 * @author Derek
 *
 */
public class GoToChevalDeFriseDownPosition extends Command
{

	
	/**
	 * I need an arm!
	 */
    public GoToChevalDeFriseDownPosition()
    {
        requires(SubsystemFactory.getSubsystemFactory().getArm());
    }

    /**
     * Nothing
     * 
     */
    protected void initialize()
    {

    }

    /**
     * Move to state
     * 
     */
    protected void execute()
    {
        SubsystemFactory.getSubsystemFactory().getArm().setState(ArmState.CHEVAL_DE_FRISE_DOWN);
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
     * Stop that arm!
     * 
     */
    protected void end()
    {
        SubsystemFactory.getSubsystemFactory().getArm().stopArm();
    }

    /**
     * When you steal the arm I stop running
     * 
     */
    protected void interrupted()
    {
        end();
    }

}
