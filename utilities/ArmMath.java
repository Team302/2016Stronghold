package org.usfirst.frc.team302.robot.utilities;


/**
 * This class performs all of the math required for the arm during competition. This
 * includes operations such as calculating the extension of the arm beyond the frame
 * perimeter, calculating the height of the end effector of the arm, and calculating
 * the angle of the shoulder joint and the elbow joint.
 * 
 * <h1>Change Log:</h1>
 * 
 * @version version 1: 3/6/2016 -- Derek Witcpalek
 *          <p>
 *          Decided to use this class for the actual code. Cleaned up the methods and
 *          variable names and added in documentation. Additionally, added in methods
 *          to calculate the angle of each joint of the arm using the full length of
 *          the linear actuator. The class still needs more documentation and some of
 *          the methods have yet to be completed.
 *          </p>
 *          
 * @version version 2: 3/7/2016 -- Derek Witcpalek
 *          <p>
 *          The methods to get angles based on the linear actuator length have been 
 *          completed. Next, the code just needs to be tested and needs to have more
 *          documentation.
 *          </p>
 *          
 * @version version 3: 3/7/2016 -- Derek Witcpalek
 *          <p>
 *          There is now more documentation in this class to describe the methods.
 *          </p>
 *          
 * @author Derek
 *
 */
public class ArmMath
{

    private static final double m_BaseArmHeight = 8.58; // this is the height the base of the arm is off the
    // ground
    private static final double m_ShoulderJointLength = 29; // this is the length of the first arm segment (pivot
    // to pivot)
    private static final double m_ElbowJointLength = 43.25; // this is the length of the second arm segment
    // (pivot to end effector)
    private static final double m_MountToFrameDistance = 1.62; // this is the distance the arm is mounted in from
    // the outside of the frame
    
    
    private static final double m_DistanceFromLowerShoulderActuatorPivotToFirstArmJointPivot = 28.51; 
    private static final double m_DistanceFromUpperShoulderActuatorPivotToFirstArmJointPivot = 10.77;
    private static final double m_DistanceFromLowerElbowActuatorPivotToSecondArmJointPivot = 5.07; 
    private static final double m_DistanceFromUpperElbowActuatorPivotToSecondArmJointPivot = 23.51; 
    
    
    private static final double m_AngleBetweenUpperElbowPivotAndStraightLineFromSecondArmPivotToEndEffectorMeasuredFromTheSecondArmPivot = 4.9; 
    private static final double m_AngleBetweenFirstArmJointPivotAndLongSegmentOfFirstJointMeasuredFromTheSecondArmPivot = 15.06; 
    private static final double m_AngleBetweenStraightLineFromFirstPivotToUpperShoulderActuatorPivotAndStraightLineFromFirstToSecondPivot = 16; 
    private static final double m_AngleBetweenStraightLineFromFirstPivotToLowerShoulderJointAndHorizontal = 1.6;
    
    
    private static ArmAngleDescriptor angles = new ArmAngleDescriptor();
    
    
    /**
     * This class this provides a convenient object to return arm angle data
     * and has methods to get and set the shoulder and elbow angles.
	 * 
	 * <h1>Change Log:</h1>
	 * 
	 * @version version 1: 3/7/2016 -- Derek Witcpalek
	 *          <p>
	 *          Initial creation and documentation
	 *          </p>
	 *          
     * @author Derek
     *
     */
    private static class ArmAngleDescriptor
    {
    	private double m_ShoulderAngle;
    	private double m_ElbowAngle;
    	
    	
    	/**
    	 * Creator - initially sets the angles both to zero
    	 */
    	private ArmAngleDescriptor()
    	{
    		m_ShoulderAngle = 0;
    		m_ElbowAngle = 0;
    	}
    	
    	
    	/**
    	 * This will update the shoulder angle stored in this object's
    	 * <i>m_ShoulderAngle</i>
    	 * 
    	 * @param angle		this is the angle that you want to update the
    	 * 					shoulder angle in the descriptor to
    	 */
    	public void setShoulderAngle(double angle)
    	{
    		m_ShoulderAngle = angle;
    	}
    	
    	
    	/**
    	 * This will update the shoulder angle stored in this object's
    	 * <i>m_ElbowAngle</i>
    	 * 
    	 * @param angle		this is the angle that you want to update the
    	 * 					elbow angle in the descriptor to
    	 */
    	public void setElbowAngle(double angle)
    	{
    		m_ElbowAngle = angle;
    	}
    	
    	
    	/**
    	 * This will get the shoulder angle that is stored by this object
    	 * 
    	 * @return	shoulder angle last set by <i>setShoulderAngle(angle)</i>
    	 */
    	public double getShoulderAngle()
    	{
    		return m_ShoulderAngle;
    	}
    	
    	
    	/**
    	 * This will get the elbow angle that is stored by this object
    	 * 
    	 * @return	elbow angle last set by <i>setElbowAngle(angle)</i>
    	 */
    	public double getElbowAngle()
    	{
    		return m_ElbowAngle;
    	}
    }
    
//    public ArmMath(double height, double firstJointLength, double secondJointLength, double mountToFrameDistance)
//    {
//        this.m_BaseArmHeight = height;
//        this.m_ShoulderJointLength = firstJointLength;
//        this.m_ElbowJointLength = secondJointLength;
//        this.m_MountToFrameDistance = mountToFrameDistance;
//    }

    /**
     * Use this method to get the height of the arm's end effector off of the ground
     * 
     * @param theta1	this is the angle between horizontal and the straight line between
     * 					the first pivot and the second pivot
     * @param theta2	this is the angle between the straight line between the first and 
     * 					second pivots and the straight line between the second pivot and the
     * 					center of the end effector
     * @return			height of the end effector
     */
    public static double getHeight(double theta1, double theta2)
    {
        return (m_BaseArmHeight + m_ShoulderJointLength * Math.sin(theta1 * Math.PI / 180) + m_ElbowJointLength * (Math.sin((theta2 - theta1) * Math.PI / 180)));
    }

    
    /**
     * Use this method to get the extension of the arm's end effector from the frame perimeter
     * of the robot
     * 
     * @param theta1	this is the angle between horizontal and the straight line between
     * 					the first pivot and the second pivot
     * @param theta2	this is the angle between the straight line between the first and 
     * 					second pivots and the straight line between the second pivot and the
     * 					center of the end effector
     * @return			extension of the end effector from the frame perimeter of the robot
     */
    public static double getExtension(double theta1, double theta2)
    {
        return (m_ElbowJointLength * Math.cos(((theta2 - theta1) * Math.PI / 180)) - (m_MountToFrameDistance + m_ShoulderJointLength * Math.cos(theta1 * Math.PI / 180)));
    }
    
    
    /**
     * Use this method to find the shoulder angle based on the total length of the linear
     * actuator. THIS METHOD REQUIRES THE TOTAL LENGTH OF THE ACTUATOR, NOT JUST THE EXTENSION!!!
     * 
     * @param linearActuatorLength		this is the total length of the actuator (extension + 
     * 									unextended length)
     * @return		the angle of the shoulder joint -- this is measured as an angle from the 
     * 				horizontal to the straight line between the first and second pivots
     */
    public static double getShoulderAngle(double linearActuatorLength)
    {
    	angles.setShoulderAngle((radiansToDegrees(Math.acos((Math.pow(linearActuatorLength, 2) 
    			- Math.pow(m_DistanceFromUpperShoulderActuatorPivotToFirstArmJointPivot, 2)
    			- Math.pow(m_DistanceFromLowerShoulderActuatorPivotToFirstArmJointPivot, 2)) 
    			/ (-2 * m_DistanceFromUpperShoulderActuatorPivotToFirstArmJointPivot
    					* m_DistanceFromLowerShoulderActuatorPivotToFirstArmJointPivot))))
    			- m_AngleBetweenStraightLineFromFirstPivotToUpperShoulderActuatorPivotAndStraightLineFromFirstToSecondPivot
    			+ m_AngleBetweenStraightLineFromFirstPivotToLowerShoulderJointAndHorizontal);
    	
    	return angles.getShoulderAngle();
    }
    
    
    /**
     * Use this method to find the elbow angle based on the total length of the linear
     * actuator. THIS METHOD REQUIRES THE TOTAL LENGTH OF THE ACTUATOR, NOT JUST THE EXTENSION!!!
     * 
     * @param linearActuatorLength		this is the total length of the actuator (extension + 
     * 									unextended length)
     * @return		the angle of the elbow joint -- this is measured as an angle from the straight
     * 				line between the first and second pivots and the straight line from the second
     * 				pivot to the center of the end effector
     */
    public static double getElbowAngle(double linearActuatorLength)
    {
    	angles.setElbowAngle((radiansToDegrees(Math.acos((Math.pow(linearActuatorLength, 2) - 
    			Math.pow(m_DistanceFromUpperElbowActuatorPivotToSecondArmJointPivot, 2) 
    			- Math.pow(m_DistanceFromLowerElbowActuatorPivotToSecondArmJointPivot, 2)) 
    			/ (-2 * m_DistanceFromUpperElbowActuatorPivotToSecondArmJointPivot
    					* m_DistanceFromLowerElbowActuatorPivotToSecondArmJointPivot))) 
    			- m_AngleBetweenUpperElbowPivotAndStraightLineFromSecondArmPivotToEndEffectorMeasuredFromTheSecondArmPivot 
    			+ m_AngleBetweenFirstArmJointPivotAndLongSegmentOfFirstJointMeasuredFromTheSecondArmPivot));
    	
    	return angles.getElbowAngle();
    }
    
    
    /**
     * Use this method to get an <i>ArmAngleDescriptor</i> that contains the angles of the
     * shoulder and elbow joints
     * 
     * @param shoulderLinearActuatorLength		this is the total length of the shoulder actuator
     * 											(extension + unextended length)
     * @param elbowLinearActuatorLength			this is the total length of the elbow actuator
     * 											(extension + unextended length)
     * @return	<i>ArmAngleDescriptor</i>		This object stores the shoulder and elbow angles
     * 											and has methods to get those values.
     */
    public static ArmAngleDescriptor getArmAngles(double shoulderLinearActuatorLength, double elbowLinearActuatorLength)
    {
    	getShoulderAngle(shoulderLinearActuatorLength);
    	getElbowAngle(elbowLinearActuatorLength);
    	
    	return angles;
    }
    
    
    /**
     * Use this method to convert angles in radians to an angle in degrees
     * 
     * @param radians	the angle value in radians
     * @return			the angle value in degrees
     */
    private static double radiansToDegrees(double radians)
    {
    	return (radians * (180 / Math.PI));
    }
}
