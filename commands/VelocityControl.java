package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team302.robot.utilities.PIDController;

import edu.wpi.first.wpilibj.command.Command;

public class VelocityControl extends Command {
  //these are the velocities of the left and right encoders
  double leftEncoderVelocity = 0;
  double rightEncoderVelocity = 0;

  //these are the current values for the talons
  double leftMotorValue = 0;
  double rightMotorValue = 0;

//this will be the output from the pid calculatisons
double leftOutput = 0;
double rightOutput = 0;

double leftVelocity = 0;
double rightVelocity = 0;

protected PIDController m_LPIDController;
protected PIDController m_RPIDController;

public VelocityControl(double left, double right) {
  // TODO Auto-generated constructor stub
  leftVelocity = left;
  rightVelocity = right;

  m_LPIDController = new PIDController(0.002, 0.00003, 0);
  m_RPIDController = new PIDController(0.002, 0.00003, 0);
}

// Called just before this Command runs the first time
protected void initialize() {
  SubsystemFactory.getSubsystemFactory().getDrive().resetEncoders();
  //turns on the brakes on the talons so it does not go too far in autonomous
  SubsystemFactory.getSubsystemFactory().getDrive().setBrake(true);
}


/**
* This class is used to drive the robot at a constant velocity using encoders and PID.
* @param output -- ???
*            
* @return -- ??
* 
*/

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

protected void execute() {
//gets the encoder values of the left and right sides
leftEncoderVelocity = SubsystemFactory.getSubsystemFactory().getDrive().getEncoderVelocity()[0];
rightEncoderVelocity = SubsystemFactory.getSubsystemFactory().getDrive().getEncoderVelocity()[1];

//calculate the outputs for all of the motors using the PID class
   leftOutput = (m_LPIDController.calculateOutput((leftEncoderVelocity), leftVelocity));
   rightOutput = (m_RPIDController.calculateOutput((rightEncoderVelocity), rightVelocity));

   //This takes the PID output and adds it to the current motor value

   leftMotorValue = (leftMotorValue + leftOutput);
   rightMotorValue = (rightMotorValue + rightOutput);

//this uses the makeInRange function to keep the value between -1 and 1
leftMotorValue = (makeInRange(leftMotorValue));
rightMotorValue = (makeInRange(rightMotorValue));


//SmartDashboard.putNumber("Left Motor Value", leftOutput);
//SmartDashboard.putNumber("Right Motor Value", rightOutput);
//SmartDashboard.putNumber("Left Encoder Position", leftEncoderVelocity);
//SmartDashboard.putNumber("Right Encoder Position", rightEncoderVelocity);
SubsystemFactory.getSubsystemFactory().getDrive().individualMotor(leftMotorValue, -rightMotorValue);
}

protected boolean isFinished() {
  // TODO Auto-generated method stub
return false;
  }

@Override
protected void end() {
  // TODO Auto-generated method stub
  
}

@Override
protected void interrupted() {
  // TODO Auto-generated method stub
  
}

}
