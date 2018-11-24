package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team302.robot.utilities.PIDController;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * When called, this command sets the encoders to 0 and holds that current position
 * with a PID closed-loop system.
 * 
 * @author Eric
 *
 */
public class HoldPosition extends Command
{
    private double leftEncoderPosition = 0;
    private double rightEncoderPosition = 0;
    
    //these are the current values for the talons
    double leftMotorValue = 0;
    double rightMotorValue = 0;
    
  //this will be the output from the pid calculatisons
    double leftOutput = 0;
    double rightOutput = 0;
    
    protected PIDController m_LPIDController;
    protected PIDController m_RPIDController;


    @Override
    protected void initialize()
    {
        SubsystemFactory.getSubsystemFactory().getDrive().resetEncoders();
        m_LPIDController = new PIDController(0.01, 0.00003, 0);
        m_RPIDController = new PIDController(0.01, 0.00003, 0);
        
    }

    @Override
    protected void execute()
    {
      //gets the encoder values of the left and right sides
        leftEncoderPosition = SubsystemFactory.getSubsystemFactory().getDrive().getEncoderPosition()[0];
        rightEncoderPosition = SubsystemFactory.getSubsystemFactory().getDrive().getEncoderPosition()[1];
        
      //calculate the outputs for all of the motors using the PID class
        leftOutput = (m_LPIDController.calculateOutput((leftEncoderPosition), 0));
        rightOutput = (m_RPIDController.calculateOutput((rightEncoderPosition), 0));
        
        SubsystemFactory.getSubsystemFactory().getDrive().teleopTankDrive(makeInRange(leftOutput), makeInRange(rightOutput));
    }

    private double makeInRange(double output)
    {
        double corrected = 0;

        if (output > 1)
        {
            corrected = 1;
        } else if (output < -1)
        {
            corrected = -1;
        } else
        {
            corrected = output;
        }

        return corrected;
    }
    @Override
    protected boolean isFinished()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void end()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void interrupted()
    {
        end();
    }

}
