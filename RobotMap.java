package org.usfirst.frc.team302.robot;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;

/**
 * This class stores and returns constants relating to the motors and sensors
 *
 * @version version 1: 1/16/2016 -- Zach Zweber Initial class creation, motor and sensor constants with getter methods
 * @version version 2: 1/17/2016 -- Derek Witcpalek Minimal clean up
 * @version version 3: 1/29/2016 -- Joe Witcpalek Refactor Package Name, make public static final and remove accessors Rename to RobotMap
 * @version version 4: 2/8/2016 -- Joe Witcpalek Add documentation/IDs for how the robot will be wired
 * @version version 5: 2/18/2016 -- Joe Witcpalek Updated documentation/IDs reacting to electrical wiring changes
 * @version version 6: 2/21/2016 -- Josh Baker Added Constants for the pressure sensor
 * @version version 7: 2/20/2016 -- Zach Zweber add new constants to the shooter
 * @version version 8: 2/23/2016 -- Joe Witcpalek Merge changes
 * @version version 9: 3/5/2016  -- Zach Zweber Proportional constant and offset constant added in anologue
 * @version version 10: 3/7/2016 -- Joe Witcpalek Add in Eric's changes from this weekend. Refactor some names to 
 *                                  be descriptive (e.g. cruise speed sounds like drive, not aim shooter variable)
 * @version version 11: 3/10/2016 -- Joe Witcpalek  Merge in changes from Josh
 * @version version 12: 3/10/2016 -- Joe Witcpalek Add comments
 * @version version 13: 3/11/2016 -- Joe Witcpalek Merge in Josh's and Derek's changes
 * @version version 14: 3/23/2016 -- Joe Witcpalek Merge in Josh's and Derek's changes
 * @version version 15: 3/28/2016 -- sasquatch      arm constants
 * 
 * @author Zach Zweber
 *
 */
public class RobotMap
{
  /*
   * Review Comments
   * 1) Things that are doubles should be x.y format (don't let the compiler convert for you)
   * 2) Add comments next to what things mean (for instance, LEVEL_ANGLE = -1, doesn't really
   *    make sense. Why isn't it 0.0?
   */
	//==========================================================================================
	// CAN Devices
	//==========================================================================================
    public static final int DRIVE_MOTOR_LEFT_MAIN = 1; // left drive motor - Master
    public static final int DRIVE_MOTOR_LEFT_SLAVE = 2; // left drive motor - Slave
    public static final int DRIVE_MOTOR_RIGHT_SLAVE = 3; // right drive motor - Slave
    public static final int DRIVE_MOTOR_RIGHT_MAIN = 4; // right drive motor - Master
    public static final int ARM_SHOULDER_MOTOR = 5; // CAN - 5
    public static final int ARM_ELBOW_MOTOR = 6; // CAN - 6
	//==========================================================================================
	// Analog Input Devices
	//==========================================================================================   
    public static final int ARM_SHOULDER_ANGLE_SENSOR = 3; // Analog - 0 *changed to not conflict
    public static final int ARM_ELBOW_ANGLE_SENSOR = 1; // Analog - 1
    //==========================================================================================
    // NavX thing
    //==========================================================================================
    public static final Port NAVX_PORT = SPI.Port.kMXP;

    // =========================================================================================
    // Drive Subsystem
    // -----------------------------------------------------------------------------------------
    // Two Talon SRX motor controllers drive the motors on each side of the robot (four motors
    // total). Each side will have a single gearbox, so the Talons should be set up with a
    // Master-Slave relationship between them. Encoders are wired into the Talons to provide
    // feedback.
    //
    // See CAN devices for Talon IDs
    // =========================================================================================
    public static final double MINIMAL_SPEED = 0.5;
    public static final double CLIMBING_ANGLE = 7.0;
    public static final double LEVEL_ANGLE = 0.0;
    
    public static final double ENCODER_REVOLUTION_COUNTS = 1440.0; //This is the amount of encoder counts per revolution
    public static final double INCHES_PER_REVOLUTION = (Math.PI*8.0); //This is the distance the robot travles per wheel revolution. PI*D

    // =========================================================================================
    // Auton Distances
    // =========================================================================================
    public static final double AUTON_LINE_TO_OUTER_WORKS = 98.0; //98 inches from auton line to outer works

    // =========================================================================================
    // Joysticks
    // =========================================================================================
    public static final int DRIVER_CONTROLLER = 0;
    public static final int COPILOT_CONTROLLER = 1;
    public static final int COPILOT_2_CONTROLLER = 2;
        
    // =========================================================================================
    // Math Comparisons
    // =========================================================================================
    
    public static final double ANGLE_TOL_DEGREES = 1.0;       // Default angle tolerance in Degrees
    public static final double DISTANCE_TOL_INCHES = 1.0;     // Default distance tolerance in inches
    public static final double SPEED_TOL_INCHES_PER_SEC = 1.0;// Default speed tolerance in inches per second
    
    // =========================================================================================
    // Arm Subsystem
    // -----------------------------------------------------------------------------------------
    // There are two arms with elbow and shoulder joints that will use Talon SRX motor controllers
    // to drive a sproket/chain drive at each joint. There will be Analog Hall Sensors to
    // help determine the angle at each joint.
    //
    // See CAN devices for Talon IDs, Analog Input Devices for the sensor IDs
    // =========================================================================================

    /* ==========================================================================================
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Arm Constants~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * ==========================================================================================
     */
    public static final double ARM_PROPORTIONAL_CONSTANT = 0.13;//was 0.15
    public static final double ARM_INTEGRAL_CONSTANT = 0.002;//was 0.0
    public static final double ARM_DERIVATIVE_CONSTANT = 0.0;
    
}