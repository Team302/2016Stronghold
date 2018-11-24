package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.Arm.ControlStyle;
import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;

public class SwitchArmControlMode extends Command {

	private ControlStyle m_style;
	private boolean isDone = false;
	
	public SwitchArmControlMode(ControlStyle style) {
		m_style = style;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		SubsystemFactory.getSubsystemFactory().getArm().stopArm();
	}

	@Override
	protected void execute() {
		SubsystemFactory.getSubsystemFactory().getArm().setControlStyle(m_style);
		isDone = true;
	}

	@Override
	protected boolean isFinished() {
		return isDone;
	}

	@Override
	protected void end() {
		
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
