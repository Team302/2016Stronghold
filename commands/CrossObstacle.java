
package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.Drive.obstacleDriveState;
import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;
import edu.wpi.first.wpilibj.command.Command;


//
//--------------------------------------------------------------------------------------
//  Modifications:
//  10-Mar-2016     Joe Witcpalek     Add comments
//  11-Mar-2016     Joe Witcpalek     Add Josh's changes
//--------------------------------------------------------------------------------------
//
public class CrossObstacle extends Command
{

    org.usfirst.frc.team302.robot.subsystems.Drive m_Drive;
    private String obstacle;

    public CrossObstacle(String ob)
    {
        m_Drive = SubsystemFactory.getSubsystemFactory().getDrive();
        requires(m_Drive);
        obstacle = ob;
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {
        m_Drive.setApproach();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute()
    {
        m_Drive.driveOverObstacle(obstacle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return (m_Drive.obstacleState == obstacleDriveState.DONE);
    }

    // Called once after isFinished returns true
    protected void end()
    {
        m_Drive.obstacleState = obstacleDriveState.STOPPED;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted()
    {
        
    }
}
