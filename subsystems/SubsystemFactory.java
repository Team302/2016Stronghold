package org.usfirst.frc.team302.robot.subsystems;

import org.usfirst.frc.team302.robot.RobotMap;
import org.usfirst.frc.team302.robot.subsystems.Arm.ControlStyle;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class will keep track of all of the robot's subsystems. Rather than accessing subsystems directly, you ask the subsystem 
 * factory for each of the subsystems you need, and it will give what you ask for.
 * 
 * <p>
 * Here is an example of how to access a subsystem: <i>Drive drive = SubsystemFactory.getSubsystemFactory().getDrive();</i>
 * </p>
 * 
 * 
 * <p>
 * <b>Change Log:</b>
 * </p>
 * 
 * @version version 1: 1/16/2016 -- Derek Witcpalek -- original class
 * @version version 2: 1/17/2016 -- Derek Witcpalek -- added methods to get each specific subsystem, changed <i>getSubsystem</i> method to private,
 *          changed SubsystemName enum to private
 * @version version 3: 1/29/2016 -- Joe Witcpalek -- Refactor Package Name, remove excess subsystems removed getSubsystem method and enum. Added a
 *          sample subsystem.
 * @version version 4: 2/10/2016 -- Joe Witcpalek -- Add getAimShooter (missing from files provided), so everything compiles.
 * 
 * @version version 5: 2/13/2016 -- Eldon Maffey -- Add getPunchingServo
 * 
 * @version version 6: 2/15/2016 -- Joe Witcpalek 
 * 			<p>
 * 			merged in Adam/Zach's change for the shooter aiming subsystem
 * 			</p>
 * 
 * @version version 7: 2/17/2016 -- Eric Smith
 * 			<p>
 * 			Add getShifter
 * 			</p>
 * 
 * @version version 8: 3/3/2016 -- Joe Witcpalek 
 * 			<p>
 * 			Add Review Comments
 * 			</p>
 * 
 * @version version 9: 3/12/2016 -- Derek Witcpalek
 *          <p>
 *          Added shifter documentation
 *          </p>
 *          
 * @version version 10: 3/28/2016 -- sasquatch
 *          <p>
 *          constants in robot map
 *          </p>
 * 
 * @author Derek Witcpalek
 */
public class SubsystemFactory
{

    // Subsystems
    private Drive m_drive; // Drive
    private Arm m_arm; // Arm

    // Factory
    private static SubsystemFactory m_factory = null;

    /**
     * Creator -- can not be called by you!!!
     */
    private SubsystemFactory()
    {
        m_drive = null;
        m_arm = null;
    }

    /**
     * This is used to get the subsystem factory so that you can get the subsystem you need. You need to call 
     * this before you can call getSubsytem().
     * 
     * @return SubsystemFactory
     */
    public static SubsystemFactory getSubsystemFactory()
    {
        if (m_factory == null)
        {
            m_factory = new SubsystemFactory();
        }

        return m_factory;
    }

    
    
    /* ============================================================================== */
    /* THE FOLLOWING METHODS ARE USED BY OUTSIDE CLASSES TO GAIN ACCESS TO SUBSYSTEMS */
    /* ============================================================================== */

    
    
    /**
     * Call this method to get the drive subsystem object
     * 
     * @return Drive The robot drive system object
     */
    public Drive getDrive()
    {
        if (m_drive == null)
        {
            m_drive = new Drive();
        }
        return m_drive;
    }

    /**
     * Call this method to get the arm subsystem object
     * 
     * @return Arm The robot's arm object
     */
    public Arm getArm()
    {
        if (m_arm == null)
        {
            m_arm = new Arm(ControlStyle.SET_POINT, RobotMap.ARM_PROPORTIONAL_CONSTANT, RobotMap.ARM_INTEGRAL_CONSTANT, RobotMap.ARM_DERIVATIVE_CONSTANT);
        }
        return m_arm;
    }
}
