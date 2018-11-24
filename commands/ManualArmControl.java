package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.OI;
import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team302.robot.utilities.MathCompares;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class moves the arm manually
 * 
 * <h1>Change Log: </h1>
 * 
 * @version <b>version 1:</b>
 * 			<p>
 * 				3/16/2016 --Derek Witcpalek -- class
 * 			</p>
 * 
 * @author Derek
 *
 */
public class ManualArmControl extends Command {

	
    
    
	public ManualArmControl() 
	{
		requires(SubsystemFactory.getSubsystemFactory().getArm());
		
		SmartDashboard.putNumber("arm control tolerance", 0.1);
	}
	
	@Override
	protected void initialize() 
	{
		SubsystemFactory.getSubsystemFactory().getArm().stopArm();
	}

	@Override
	protected void execute() 
	{
		SubsystemFactory.getSubsystemFactory().getArm().set(shoulderAxis(), elbowAxis());
	}

	protected double shoulderAxis()
	{
	    double axis;
	    if (!MathCompares.isEqual(OI.getOI().getCopilotJoystick().getRawAxis(1), 0.0, SmartDashboard.getNumber("arm control tolerance")))   {	      		 
	        axis = OI.getOI().getCopilotJoystick().getRawAxis(1);
	    }
	    else {
	        axis = 0;
	    }
	    return axis;
	}
	
	   protected double elbowAxis()
	    {
	        double axis;
	        if (!MathCompares.isEqual(OI.getOI().getCopilotJoystick().getRawAxis(5), 0.0, SmartDashboard.getNumber("arm control tolerance")))   {              
	            axis = OI.getOI().getCopilotJoystick().getRawAxis(5);
	        }
	        else {
	            axis = 0;
	        }
	        return axis;
	    }
	
	@Override
	protected boolean isFinished() 
	{
		return false;
	}

	@Override
	protected void end() 
	{
		SubsystemFactory.getSubsystemFactory().getArm().stopArm();		
	}

	@Override
	protected void interrupted() 
	{
		end();
	}

}
