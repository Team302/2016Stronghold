package org.usfirst.frc.team302.robot.subsystems;

import org.usfirst.frc.team302.robot.RobotMap;
import org.usfirst.frc.team302.robot.commands.ManualArmControl;
import org.usfirst.frc.team302.robot.subsystems.ArmSetpoint.ArmState;
import org.usfirst.frc.team302.robot.utilities.Actuator;
import org.usfirst.frc.team302.robot.utilities.Actuator.linearActuator;
import org.usfirst.frc.team302.robot.utilities.ArmMath;
import org.usfirst.frc.team302.robot.utilities.HallEffectSensor;
import org.usfirst.frc.team302.robot.utilities.LinearActuator;
import org.usfirst.frc.team302.robot.utilities.PIDController;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class allows control of the arm.
 * 
 * The arm is controlled by two linear actuators: one actuates the shoulder joint
 * while the other actuates the elbow. Each linear actuator has a potentiometer
 * that can be used to determine the extension of the actuator and hall effect sensors
 * to determine if the actuator is at its limits.
 * 
 * There is a PID controller for each joint of the arm that ensure that each joint
 * reaches its target position.
 * 
 * 
 * ****IT IS VITAL TO PASSING INSPECTION THAT THE ROBOT WILL NEVER REACH OUTSIDE OF THE
 * 		15 INCH EXTENSION!!! WE NEED MULTIPLE LEVELS OF VERIFICATION IN THIS CLASS TO 
 * 		VERIFY THAT IT IS NOT REACHING PAST THAT AND THAT ARE CONTROLLING THE ARM ACCORDINGLY
 * 		TO FORCE IT TO STAY INSIDE THAT LIMIT EVEN IF ANOTHER ROBOT HITS IT OR THE DRIVERS
 * 		MANUALLY TRY TO EXTEND BEYOND THAT LIMIT!!!
 * 
 * 
 * 
 * <h1>Change Log:</h1>
 * 
 * @version <b>version 1:</b> 2/2/2016 -- Jeff Tefend
 *          <p>
 *          Initial class creation, motor and sensors with ability to set states
 *          </p>
 * 
 * @version <b>version 2:</b> 2/2/2016 -- Derek Witcpalek
 *          <p>
 *          Added methods to check whether the states have been reached and to stop the motors
 *          Also added documentation
 *          </p>
 * 
 * @version <b>version 3:</b> 2/9/2016 -- Derek Witcpalek
 *          <p>
 *          Added documentation and classes to return angles based on sensor input
 *          </p>
 * 
 * @version <b>version 4:</b> 2/10/2016 -- Derek Witcpalek
 *          <p>
 *          Added adjustments for the bend in the arms
 *          </p>
 * 
 * @version <b>version 5:</b> 2/13/2016 -- Derek Witcpalek
 *          <p>
 *          Tested arm code: the arm was reaching setpoints, but there were issues with it reaching
 *          outside of the frame perimeter, and with the angles read by the potentiometer changing
 *          from test to test. The issue with the potentiometers still requires more testing, and the
 *          frame perimeter issue also needs a solution. I see two options: coordinating the outputs
 *          between the two joints or adding more setpoints that are essentially "transition" setpoints
 *          that the arm would move to between target positions.
 *          </p>
 * 
 * @version <b>version 6:</b> 2/14/2016 -- Derek Witcpalek
 *          <p>
 *          Added in coordination of control between the two joints of the arm so that the arm will
 *          be less likely to reach outside of the frame. Additionally, there is now the option to
 *          control the arm either manually or with setpoints. When controlling the arm manually,
 *          there is now code to make sure that it doesn't go outside of the extension limit. These
 *          new things still need to be tested.
 *          </p>
 * 
 * @version <b>version 7:</b> 2/16/2016 -- Derek Witcpalek
 *          <p>
 *          Removed deprecated methods
 *          </p>
 * 
 * @version <b>version 8:</b> 2/17/2016 -- Derek Witcpalek
 *          <p>
 *          Renamed things better -- motor names are now shoulder and elbow motor and the
 *          PID controllers also follow this naming model
 *          </p>
 *          
 * @version <b>version 9:</b> 3/5/2016 -- Derek Witcpalek
 *          <p>
 *          Updated the arm setpoints to be accessed through the <i>ArmSetpoint</i> class
 *          </p>
 *          
 * @version <b>version 10:</b> 3/6/2016 -- Derek Witcpalek
 *          <p>
 *          Added in objects of the linear actuator class and added documentation to this
 *          class. The actuator objects still need to be used instead of just motors, and
 *          the <i>LinearActuator</i> class may need to be changed to have an initial offset
 *          for the potentiometer. Additionally, this version updates the <i>isAtTargetState(tolerance)</i>
 *          method so that both the shoulder joint and the elbow joint are checked, fixing
 *          the issues from before.
 *          </p>
 * 
 * @version <b>version 11:</b> 3/12/2016 -- Derek Witcpalek
 *          <p>
 *          Cleaned up some of the code and alphabetized the methods 
 *          </p>
 *          
 * @version <b>version 12:</b> 3/21/2016 -- Derek Witcpalek
 *          <p>
 *          Added in hall effect sensors to measure the angles and added documentation for the
 *          <i>dashboardInformation()</i> method. I also alphabetized all of the methods so that
 *          they can be found more easily.
 *          </p>
 * 
 * @author Jeff Tefend
 *
 */
public class Arm extends Subsystem
{

    // full down position:
    // first joint ~~ 20 degrees
    // second joint ~~ 0 degrees

    // private final double m_CountsPerRevolution = 1440; //100

	// TODO - put constants into RobotMap

    //private final double m_MaxManualExtension = 13.0;

    private ControlStyle m_ControlStyle;
    
    //linear actuators
    private LinearActuator m_ShoulderActuator;
    private LinearActuator m_ElbowActuator;

    // Add PID Controller
    private PIDController m_ShoulderController;
    private PIDController m_ElbowController;
    
    //Add angle sensors at joints
    private HallEffectSensor m_ShoulderAngleSensor;
    private HallEffectSensor m_ElbowAngleSensor;

    // initialize the state that the arm is trying to reach
    private ArmSetpoint m_currentState = ArmSetpoint.getPosition(ArmState.STARTING_POSITION);

    
    /**
     * These are the types of control for the arm.
     * You can either control the arm by driving it
     * to setpoints, or by driving it with joystick
     * or other driver input.
     */
    public enum ControlStyle
    {
        MANUAL, 		//Move the arm via user input for each joint
        SET_POINT		//Move the arm to specific setpoints as requested by the user
    }

    
    /**
     * Creator: creates an arm object
     * 
     * @param style
     *            this is a control style: manual or setpoint-controlled
     * @param pidConstant
     *            this is an array of the pid constants for the arm controllers.
     *            It is in the form: {p-constant, i-constant, d-constant}
     */
    public Arm(ControlStyle style, double[] pidConstant)
    {
        this(style, pidConstant[0], pidConstant[1], pidConstant[2]);
    }

    
    /**
     * Creator: creates an arm object
     * 
     * @param style
     *            this is a control style: manual or setpoint-controlled
     */
    public Arm(ControlStyle style)
    {
        this(style, 0.0, 0.0, 0.0);
    }

    
    /**
     * Creator: creates an arm object
     * 
     * @param style
     *            this is a control style: manual or setpoint-controlled
     * @param proportionalConstant
     *            proportional constant
     * @param integralConstant
     *            integral constant
     * @param derivativeConstant
     *            derivative constant
     */
    public Arm(ControlStyle style, double proportionalConstant, double integralConstant, double derivativeConstant)
    {

        m_ControlStyle = style;
        
        // create actuators
        m_ShoulderActuator = new LinearActuator(RobotMap.ARM_SHOULDER_MOTOR, 0.0);
        m_ElbowActuator = new LinearActuator(RobotMap.ARM_ELBOW_MOTOR, 1.514);
        
        // Add PID Controller for each joint
        m_ShoulderController = new PIDController(proportionalConstant, integralConstant, derivativeConstant);
        m_ElbowController = new PIDController(proportionalConstant, integralConstant, derivativeConstant);
        
        // Create sensors
        m_ShoulderAngleSensor = new HallEffectSensor(RobotMap.ARM_SHOULDER_ANGLE_SENSOR, 8.289);//-13.683//2.528
        m_ElbowAngleSensor = new HallEffectSensor(RobotMap.ARM_ELBOW_ANGLE_SENSOR, 11.556);//-14.171//-9.581
   }

    
    /**
     * Display whether the arm is outside the frame perimeter on the smart dashboard
     * 
     * Also displays the arm's current target state
     */
    public void dashboardInformation()
    {
        double extension = ArmMath.getExtension(getShoulderAngle(), getElbowAngle());
        if (extension > 15)
        {
            SmartDashboard.putString("Is Arm Outside Perimeter?", "Yes");
        }
        else
        {
            SmartDashboard.putString("Is Arm Outside Perimeter?", "No");
        }
        SmartDashboard.putString("Arm Target State", getArmStateTargetName());
    }


    /**
	 * Use this to tell where the arm is trying to get to.
	 * The state information returned reflects the state that
	 * was passed into <i>setState()</i> the last time it was
	 * called.
	 * 
	 * @return ArmState The state that the arm is currently trying to reach
	 * 					This includes all of the numbers about the position
	 * 					as well as a name of the position.
	 */
	public ArmSetpoint getArmStateTarget()
	{
	    return m_currentState;
	}


	/**
	 * Use this to tell which position the arm is trying to
	 * get to. The state name returned reflects the state that
	 * was passed into <i>setState()</i> the last time it was
	 * called.
	 * 
	 * @return	name of the position
	 */
	public String getArmStateTargetName()
	{
		return m_currentState.getName();
	}
	
	public ControlStyle getControlStyle()
	{
	    return m_ControlStyle;
	}


	/**
	 * Get the angle of the elbow joint
	 * 
	 * @return average angle
	 */
	public double getElbowAngle()
	{
	    // this is the angle between the line that goes straight between the pivots of the arm,
	    // and the line that goes between the second pivot of the arm and the center of
	    // the hooking mechanism
	    
	    
	    //law of cosines method
		//return ArmMath.getElbowAngle(m_ElbowActuator.getLength());
	    
	    //hall effect sensor value
	    return m_ElbowAngleSensor.getAngle();
	}


	/**
	 * Get the angle of the shoulder joint
	 * 
	 * @return average angle
	 */
	public double getShoulderAngle()
	{
	    //law of cosines method
//		return ArmMath.getShoulderAngle(m_ShoulderActuator.getLength());
	    
	    //hall effect sensor value
	    return m_ShoulderAngleSensor.getAngle();
	}


	/**
	 * {@inheritDoc}
	 */
	protected void initDefaultCommand()
	{
		setDefaultCommand(new ManualArmControl());
	}


	/**
	 * This will tell you if the arm is within the specified tolerance of its target.
	 * 
	 * If it is within that tolerance value, the method returns true
	 * 
	 * @param tolerance		How close does the arm have to be to the target to
	 * 						be considered at that state? (in degrees)
	 *            
	 * @return isAtState is the arm within your tolerance threshold of the target state
	 */
	public boolean isAtTargetState(double tolerance)
	{
	    SmartDashboard.putNumber("Shoulder error", m_ShoulderController.getError());
	    SmartDashboard.putNumber("Elbow Error", m_ElbowController.getError());
	    return (Math.abs(m_ShoulderController.getError()) < tolerance && 
	            Math.abs(m_ElbowController.getError()) < tolerance);
	}


	/**
	 * Sets the motors to a specific output when you are using manual control.
	 * 
	 * 
	 * NOT YET IMPLEMENTED: This method will not let you exceed a specific maximum extension when
	 * 						controlling the robot's arm manually.
	 * 
	 * 
	 * @param shoulderJoint	This is the output for the shoulder joint of the arm
	 *            
	 * @param elbowJoint	This is the output for the elbow joint of the arm
	 *            
	 */
	public void set(double shoulderJoint, double elbowJoint)//TODO add limitation to extension with manual control
	{
	    // double extension = ArmMath.getExtension(getFirstJointPosition(), getSecondJointPosition());
	    //
	    // if(m_ControlStyle == ControlStyle.MANUAL)
	    // {
	    // if(output1 > 0)
	    // {
	    // if(extension >= m_MaxManualExtension)
	    // {
	    // m_LeftSideLowerJoint.set(0);
	    // //m_RightSideLowerJoint.set(0);
	    // SmartDashboard.putNumber("Arm output 1", 0);
	    // }
	    // else
	    // {
	    // m_LeftSideLowerJoint.set(output1);
	    // //m_RightSideLowerJoint.set(output1);
	    // SmartDashboard.putNumber("Arm output 1", output1);
	    // }
	    //
	    // }
	    // else if(output1 < 0)
	    // {
	    // m_LeftSideLowerJoint.set(output1);
	    // //m_RightSideLowerJoint.set(output);
	    // SmartDashboard.putNumber("Arm output 1", output1);
	    // }
	    //
	    // }
	    // if(output2 > 0)
	    // {
	    // if(extension >= m_MaxManualExtension)
	    // {
	    // if(getSecondJointPosition()-getFirstJointPosition() < 0)
	    // {
	    // m_LeftSideUpperJoint.set(0);
	    // m_RightSideUpperJoint.set(0);
	    // SmartDashboard.putNumber("Arm output 2", 0);
	    // }
	    // else if(getSecondJointPosition()-getFirstJointPosition() >= 0)
	    // {
	    // m_LeftSideUpperJoint.set(output2);
	    // m_RightSideUpperJoint.set(output2);
	    // SmartDashboard.putNumber("Arm output 2", output2);
	    // }
	    // }
	    //
	    // }
	    // else if(output2 < 0)
	    // {
	    // if(extension >= m_MaxManualExtension)
	    // {
	    // if(getSecondJointPosition()-getFirstJointPosition() > 0)
	    // {
	    // m_LeftSideUpperJoint.set(0);
	    // m_RightSideUpperJoint.set(0);
	    // SmartDashboard.putNumber("Arm output 2", 0);
	    // }
	    // else if(getSecondJointPosition()-getFirstJointPosition() <= 0)
	    // {
	    // m_LeftSideUpperJoint.set(output2);
	    // m_RightSideUpperJoint.set(output2);
	    // SmartDashboard.putNumber("Arm output 2", output2);
	    // }
	    // }
	    // }
	    // SmartDashboard.putNumber("Arm input 1", output1);
	    // SmartDashboard.putNumber("Arm input 2", output2);
	    if (m_ControlStyle == ControlStyle.MANUAL)
	    {
	    	m_ShoulderActuator.set(shoulderJoint, getShoulderAngle(), Actuator.getActuator(linearActuator.SHOULDER));
	    	m_ElbowActuator.set(elbowJoint, getElbowAngle(), Actuator.getActuator(linearActuator.ELBOW));
	    	SmartDashboard.putNumber("first joint output", shoulderJoint);//TODO remove smart dashboard stuff
	    	SmartDashboard.putNumber("second joint output", elbowJoint);
	    }
	    
//	    SmartDashboard.putNumber("Shoulder Angle", m_ShoulderAngleSensor.getAngle());
//	    SmartDashboard.putNumber("Elbow actuator angle", m_ElbowAngleSensor.getAngle());
//	    
//	    SmartDashboard.putNumber("Shoulder Actuator Extension", m_ShoulderActuator.getExtension());
//	    SmartDashboard.putNumber("Elbow Actuator Extension", m_ElbowActuator.getExtension());
//	    
//	    SmartDashboard.putNumber("Shoulder Output", shoulderJoint);
//	    SmartDashboard.putNumber("Elbow Output", elbowJoint);
//	    
//	    SmartDashboard.putNumber("Extension", ArmMath.getExtension(m_ShoulderAngleSensor.getAngle(), m_ElbowAngleSensor.getAngle()));
	}


	/**
     * Changes the arm's control style to the input style.
     * This can be used to accommodate different preferences.
     * 
     * This will only be used to make in-match changes to the control
     * style. If a driver wants to change the default, go to the
     * subsystem factory class and change the object that is created
     * there.
     * 
     * @param style
     *            This is the method you want to use to control the arm
     */
    public void setControlStyle(ControlStyle style)
    {
        m_ControlStyle = style;
    }

    
    /**
     * This can be used when testing to change the pid constants
     * 
     * ***THIS METHOD SHOULD NOT BE USED DURING MATCHES!!!
     * 
     * @param proportionalConstant
     *            proportional
     * @param integralConstant
     *            integral
     * @param derivativeConstant
     *            derivative
     */
    public void setPIDConstants(double proportionalConstant, double integralConstant, double derivativeConstant)
    {
        m_ShoulderController.setPIDConstants(proportionalConstant, integralConstant, derivativeConstant);
        m_ElbowController.setPIDConstants(proportionalConstant, integralConstant, derivativeConstant);
    }

    
    /**
     * Set the target state for the arm. The arm will proceed to move to
     * this state. This method needs to be called continuously in order to
     * get the arm to the desired state.
     * 
     * NOTE: THIS METHOD ONLY WORKS WHEN THE ARM IS IN THE SETPOINT CONTROL
     * 		 MODE
     * 
     * @param target	this is the state that you want the arm to go to
     */
    public void setState(ArmState target)
    {

//        m_ShoulderController.setPIDConstants(0.15, 0.0, 0.0);
//        m_ElbowController.setPIDConstants(0.15, 0.0, 0.0);

//        SmartDashboard.putNumber("Shoulder Angle", m_ShoulderAngleSensor.getAngle());
//        SmartDashboard.putNumber("Elbow actuator angle", m_ElbowAngleSensor.getAngle());
        
        
        if (m_ControlStyle == ControlStyle.SET_POINT)
        {
            // update the target state of the arm
            m_currentState = ArmSetpoint.getPosition(target);

            // Calculate outputs
            double output = m_ShoulderController.calculateOutput(getShoulderAngle(), ArmSetpoint.getPosition(target).getShoulderJointAngle());
            double secondOutput = m_ElbowController.calculateOutput(getElbowAngle(), ArmSetpoint.getPosition(target).getElbowJointAngle());
            
            SmartDashboard.putNumber("Shoulder Output", output);
            SmartDashboard.putNumber("Elbow Output", secondOutput);
            
            m_ShoulderActuator.set(-output, getShoulderAngle(), Actuator.shoulder);
            SmartDashboard.putNumber("first joint output", output);//TODO remove smart dashboard stuff

            m_ElbowActuator.set(-secondOutput, getElbowAngle(), Actuator.elbow);
            SmartDashboard.putNumber("second joint output", secondOutput);
        }
    }
    
    /**
	 * Sends an output of zero to all of the arm motors
	 * 
	 * NOTE:	ALTHOUGH THIS SHOULD STOP THE ARM, IT MAY BE
	 * 			POSSIBLE TO MOVE THE ARM ANYWAY DEPENDING ON
	 * 			ITS NATURAL RESISTANCE TO MOTION
	 */
	public void stopArm()
	{
		m_ShoulderActuator.set(0.0);
		m_ElbowActuator.set(0.0);
	}
}
