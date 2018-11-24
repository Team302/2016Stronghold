package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team302.robot.subsystems.Arm.ControlStyle;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwitchArmMode extends Command
{
    
    private boolean switched = false;
    
    public SwitchArmMode()
    {
//        requires(SubsystemFactory.getSubsystemFactory().getArm());
    }

    @Override
    protected void initialize()
    {
        // TODO Auto-generated method stub
        switched = false;
    }

    @Override
    protected void execute()
    {
        SmartDashboard.putBoolean("Switching arm mode", true);
        if(SubsystemFactory.getSubsystemFactory().getArm().getControlStyle() == ControlStyle.MANUAL && !switched)
        {
            SubsystemFactory.getSubsystemFactory().getArm().setControlStyle(ControlStyle.SET_POINT);
            switched = true;
        }
        else if(SubsystemFactory.getSubsystemFactory().getArm().getControlStyle() == ControlStyle.SET_POINT && !switched)
        {
            SubsystemFactory.getSubsystemFactory().getArm().setControlStyle(ControlStyle.MANUAL);
            switched = true;
        }
        else
        {
            SubsystemFactory.getSubsystemFactory().getArm().setControlStyle(ControlStyle.MANUAL);
            switched = true;
        }
        
    }

    @Override
    protected boolean isFinished()
    {
        // TODO Auto-generated method stub
        return switched;
    }

    @Override
    protected void end()
    {
        // TODO Auto-generated method stub
        SmartDashboard.putBoolean("Switching arm mode", false);
    }

    @Override
    protected void interrupted()
    {
        // TODO Auto-generated method stub
        
    }

}
