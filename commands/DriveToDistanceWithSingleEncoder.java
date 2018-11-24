package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;

public class DriveToDistanceWithSingleEncoder extends Command {

	
	private double m_target = 0;
	private double m_startSpeed = 0;
	private double m_endSpeed = 0;
	private double m_maxSpeed = 0;
	
	public DriveToDistanceWithSingleEncoder(double distance, double startSpeed, double endSpeed, double maxSpeed) {
		m_target = distance;
		m_startSpeed = startSpeed;
		m_endSpeed = endSpeed;
		m_maxSpeed = maxSpeed;
		requires(SubsystemFactory.getSubsystemFactory().getDrive());
	}
	
	@Override
	protected void initialize() {
		SubsystemFactory.getSubsystemFactory().getDrive().resetEncoders();
		SubsystemFactory.getSubsystemFactory().getDrive().resetVelocityStateForDistanceDriving();
	}

	@Override
	protected void execute() {
		SubsystemFactory.getSubsystemFactory().getDrive().driveToDistanceAndKeepHeadingVelocityControl(m_target, m_startSpeed, m_endSpeed, m_maxSpeed);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return (SubsystemFactory.getSubsystemFactory().getDrive().isAtDistanceWithSingleEncoder(m_target, 1));
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		SubsystemFactory.getSubsystemFactory().getDrive().stop();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
