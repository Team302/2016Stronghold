package org.usfirst.frc.team302.robot.utilities;

import org.usfirst.frc.team302.robot.RobotMap;
import com.kauailabs.navx.frc.AHRS;

/**
 * This class wraps the NavX sensor to provide the orientations that are based on 
 * roborio/NavX installation orientation.  For instance, pitch and roll are reversed.
 * 
 * @author Klayton
 */
//
//-----------------------------------------------------------------------------
//  Modifications:
//  10-Mar-2016     Joe Witcpalek         Added documentation to the class
//                                        Fixed the singleton set up of the class
//                                        as callers should interact with this class and not
//                                        the AHRS class.  Note: there are many
//                                        more methods on the AHRS class.
//   11-Mar-2016     Joe Witcpalek        Merge in Josh's changes & made
//									      consistent.  If methods are static, 
//                                        they need to make sure the AHRS class 
//									      has been created.
//-----------------------------------------------------------------------------
//

/**
 * This is a wrapper around the navX board.
 * 
 * The board is mounted on the robot such that pitch/roll are reversed, so to the calls don't make
 * sense in robot terms.  So, we are wrapping the class and either passing through calls or adapting 
 * the calls to make sense in robot terms. 
 * 
 * This is a singleton class.
 * 
 * @author Klayton Smith
 *
 */

public class NavXStuffs
{
    private static AHRS m_navX;                 // NavX class that gets interacted with
    
   /**
    * Private constructor that only allows the public static method to create this class.
    */
    private NavXStuffs()
    {
        m_navX = new AHRS(RobotMap.NAVX_PORT);
    }
    
  /**
   * This method will reset the navX board.
   */
    public static void reset()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        m_navX.reset();
    }
    
  /**
   * Returns the current yaw value (in degrees, from -180 to 180) reported by the NavX board.
   * 
   * @return yaw value between -180 and 180 degrees
   */
    public static double getYaw()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        return m_navX.getYaw();
    }
    
  /**
   * Returns the current pitch value (in degrees, from -180 to 180) reported by the NavX board. 
   * Pitch is a measure of rotation front-to-back or back-to-front.
   * 
   * @return The current pitch value in degrees (-180 to 180).
   */
    public static double getPitch()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        return m_navX.getRoll();
    }
    
  /**
   * Returns the current roll value (in degrees, from -180 to 180) reported by the NavX board. 
   * Roll is a measure of rotation left-to-right or right-to-left.
   * 
   * @return   The current roll value in degrees (-180 to 180).
   */
    public static double getRoll()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        return m_navX.getPitch();
    }
    
  /**
   * Returns the displacement (in meters) of the Y axis since resetDisplacement() was last 
   * invoked [Experimental]. NOTE: This feature is experimental. Displacement measures rely 
   * on double-integration of acceleration values from MEMS accelerometers which yield "noisy" 
   * values. The resulting displacement are not known to be very accurate, and the amount of 
   * error increases quickly as time progresses.

   * @return Displacement since last reset (in meters).
   */
    public static float getDisplacementY()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        return m_navX.getDisplacementX();
    }
    
  /**
   * Returns the displacement (in meters) of the X axis since resetDisplacement() was last 
   * invoked [Experimental]. NOTE: This feature is experimental. Displacement measures rely 
   * on double-integration of acceleration values from MEMS accelerometers which yield "noisy" 
   * values. The resulting displacement are not known to be very accurate, and the amount of 
   * error increases quickly as time progresses.
   * 
   * @return Displacement since last reset (in meters).
   */
    public static double getDisplacementX()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        return m_navX.getDisplacementY();
    }
    
  /**
   * Returns the displacement (in meters) of the Z axis since resetDisplacement() was last 
   * invoked [Experimental]. NOTE: This feature is experimental. Displacement measures rely 
   * on double-integration of acceleration values from MEMS accelerometers which yield "noisy" 
   * values. The resulting displacement are not known to be very accurate, and the amount of 
   * error increases quickly as time progresses.
   * 
   * @return Displacement since last reset (in meters).
   */    
    public static double getDisplacementZ()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        return m_navX.getDisplacementZ();
    }
    
   /**
    * Returns the total accumulated yaw angle (Z Axis, in degrees) reported by the NavX board.
    * 
    * NOTE: The angle is continuous, meaning it's range is beyond 360 degrees. This ensures that 
    * algorithms that wouldn't want to see a discontinuity in the gyro output as it sweeps past 0
    * on the second time around.
    * 
    * Note that the returned yaw value will be offset by a user-specified offset value; this 
    * user-specified offset value is set by invoking the zeroYaw() method.
    * 
    * @return The current total accumulated yaw angle (Z axis) of the robot in degrees. This 
    * heading is based on integration of the returned rate from the Z-axis (yaw) gyro.
    */
    public static double getAngle()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        return m_navX.getAngle();
    }
    
    /**
     * Indicates if the sensor is currently detecting motion, based upon the X and Y-axis world 
     * linear acceleration values. If the sum of the absolute values of the X and Y axis exceed a "motion threshold", the motion state is indicated.
     * 
     * @return Returns true if the sensor is currently detecting motion.

     */
    public static boolean isMoving()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        return m_navX.isMoving();
    }
    
    /**
     * Indicates if the sensor is currently detecting yaw rotation, based upon whether the change 
     * in yaw over the last second exceeds the "Rotation Threshold."
     * 
     * Yaw Rotation can occur either when the sensor is rotating, or when the sensor is not rotating
     *  AND the current gyro calibration is insufficiently calibrated to yield the standard yaw 
     *  drift rate.
     *  
     *  @return  Returns true if the sensor is currently detecting motion.
     */
    public static boolean isRotationg()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        return m_navX.isRotating();
    }
    
    /**
     * Return the rate of rotation of the yaw (Z-axis) gyro, in degrees per second.
     * 
     * @return The rate is based on the most recent reading of the yaw gyro angle.
     */
    public static double getRotationRate()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        return m_navX.getRate();
    }    
    
    /**
     * Returns the version number of the firmware currently executing on the sensor.
     * 
     * To update the firmware to the latest version, please see:
     *   http://navx-mxp.kauailabs.com/navx-mxp/support/updating-firmware/  
     *      * 
     * @return The firmware version in the format [MajorVersion].[MinorVersion]
     */
    public static java.lang.String getFirmwareVersion()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        return m_navX.getFirmwareVersion();
    }
    
    /**
     * Sets the user-specified yaw offset to the current yaw value reported by the sensor.
     */
    public static void zeroYaw()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        m_navX.zeroYaw();
    }
    
    /**
     * Returns true if the sensor is currently performing automatic gyro/accelerometer calibration. 
     * Automatic calibration occurs when the sensor is initially powered on, during which time the 
     * sensor should be held still, with the Z-axis pointing up (perpendicular to the earth).
     * 
     * NOTE: During this automatic calibration, the yaw, pitch and roll values returned may not be 
     * accurate.
     * 
     * Once calibration is complete, the sensor will automatically remove an internal yaw offset 
     * value from all reported values.
     * 
     * @return Returns true if the sensor is currently automatically calibrating the gyro and 
     * accelerometer sensors.
     */
    public static boolean isCalibrating()
    {
    	if ( m_navX == null )
    	{
    		m_navX = new AHRS( RobotMap.NAVX_PORT);
    	}
        return m_navX.isCalibrating();    
    }
}
    
