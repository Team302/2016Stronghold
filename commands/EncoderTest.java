
package org.usfirst.frc.team302.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team302.robot.subsystems.Drive;
import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;
/**
 * This class is used to test encoders.
 * It just puts the position and velocity values for the left and right encoderson the smart dashboard.
 */
//NOTE: ON ROBOT, WHEELS SPIN 1 TIME FOR EVERY 3 ROTATIONS OF THE ENCODER
public class EncoderTest extends Command {

        //these are the velocities of the left and right encoders
        double leftEncoderVelocity = 0;
        double rightEncoderVelocity = 0;

        //these are the positions of the left and right encoders
        double leftEncoderPosition = 0;
        double rightEncoderPosition = 0;

    protected Drive m_drive;
    public EncoderTest() {
        // Use requires() here to declare subsystem dependencies
        m_drive = SubsystemFactory.getSubsystemFactory().getDrive();
        requires(m_drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        m_drive.resetEncoders();
    }
   // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    protected void execute()
    {
      //gets encoder velocities from the drive class
      leftEncoderVelocity = m_drive.getEncoderVelocity()[0];
      rightEncoderVelocity = m_drive.getEncoderVelocity()[1];

      leftEncoderPosition = m_drive.getEncoderPosition()[0];
      rightEncoderPosition = m_drive.getEncoderPosition()[1];
    }
}
