package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;

public class NoMove extends Command
{
    org.usfirst.frc.team302.robot.subsystems.Drive m_Drive;
    
    public NoMove()
    {
        m_Drive = SubsystemFactory.getSubsystemFactory().getDrive();
        requires(m_Drive);
    }
    
    protected void initialize()
    {
        
    }
    
    protected void execute()
    {
        m_Drive.stop();
    }
    
    protected boolean isFinished()
    {
        return false;
    }
    
    protected void end()
    {
        
    }
    
    protected void interrupted()
    {
        end();
    }
}