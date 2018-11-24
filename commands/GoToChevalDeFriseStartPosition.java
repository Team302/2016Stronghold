package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.ArmSetpoint.ArmState;
import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;


/**
 * This class moves the arm to the cheval de frise start position
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
public class GoToChevalDeFriseStartPosition extends Command
{

	
	/**
	 * I need an arm
	 */
    public GoToChevalDeFriseStartPosition()
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
     * moves arm
     * 
     */
    protected void execute()
    {
        SubsystemFactory.getSubsystemFactory().getArm().setState(ArmState.CHEVAL_DE_FRISE_START);
    }

    /**
     * Are ye at state?
     * 
     */
    protected boolean isFinished()
    {
        return SubsystemFactory.getSubsystemFactory().getArm().isAtTargetState(3);
    }

    /**
     * Arm stop moving!
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
