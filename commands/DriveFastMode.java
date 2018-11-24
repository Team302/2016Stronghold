package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.command.Command;

public class DriveFastMode extends Command
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
        SubsystemFactory.getSubsystemFactory().getDrive().setSlowMode(false);
        SubsystemFactory.getSubsystemFactory().getDrive().setFastMode(true);
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
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub
        
    }

}
