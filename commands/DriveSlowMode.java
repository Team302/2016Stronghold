package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;

public class DriveSlowMode extends Command
{

    private boolean m_finished = false;
    
    @Override
    protected void initialize()
    {
        m_finished = false;
    }

    @Override
    protected void execute()
    {
        SubsystemFactory.getSubsystemFactory().getDrive().setSlowMode(true);
        SubsystemFactory.getSubsystemFactory().getDrive().setFastMode(false);
        m_finished = true;
    }

    @Override
    protected boolean isFinished()
    {
        return m_finished;
    }

    @Override
    protected void end()
    {
    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub
        
    }

    
}
