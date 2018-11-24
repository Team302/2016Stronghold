
package org.usfirst.frc.team302.robot.subsystems;

import org.usfirst.frc.team302.robot.utilities.NavXStuffs;
import org.usfirst.frc.team302.robot.RobotMap;
import org.usfirst.frc.team302.robot.commands.CommandFactory;
import org.usfirst.frc.team302.robot.utilities.AngularMath;
import org.usfirst.frc.team302.robot.utilities.MathCompares;
import org.usfirst.frc.team302.robot.utilities.PIDController;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This subsystem defines the drive subsystem for the robot. It controls the talons/motors for the drive as well as processes the encoder values.
 * 
 * @version <b>version 1:</b> 1/16/2016 -- Derek Witcpalek -- original dummy class
 * @version <b>version 2:</b> 2/1/2016 -- Eric Smith and Klayton Smith -- Fill in the details
 * @version <b>version 3:</b> 2/21/2016 -- Derek Witcpalek -- Update with turn control for arcade drive
 * @version <b>version 4:</b> 2/21/2016 -- Klayton Smith -- Cross obstacle
 * @version <b>version 5:</b> 2/27/2016 -- Josh Baker -- lowBar + implemented navX wrapper
 * @version <b>version 6:</b> 3/3/2016  -- Derek Witcpalek -- Add Jeff's turn to angle and vision-based orientation
 * @version <b>version 7:</b> 3/3/2016 -- Joe Witcpalek Merge -- Derek's changes with the latest version
 * @version <b>version 8:</b> 3/3/2016 -- Joe Witcpalek Add Review comments
 * @version <b>version 9:</b> 3/7/2016 -- Joe Witcpalek -- Add in Eric's changes from this weekend
 * @version <b>version 10</b> 3/10/2016 -- Joe Witcpalek -- Merge in Eric and Josh's changes
 * @version <b>version 11</b> 3/12/2016 -- Sasquatch -- General Edits
 * @version <b>version 12</b> 3/23/2016 -- Joe Witcpalek - merge Derek and Josh's changes
 * 
 */
public class Drive extends Subsystem
{
    private RobotDrive m_drive;
    private CANTalon m_leftMotor;
    private CANTalon m_rightMotor;
    private CANTalon m_leftMotorB;
    private CANTalon m_rightMotorB;
    private PIDController m_LPIDController;
    private PIDController m_RPIDController;
    private PIDController m_TurningController;
    private PIDController m_TargetingController;
//    private PIDController m_DistanceController;
    private boolean m_isTargetHeadingSet = false;
    private double m_targetHeading;

    private double m_decelerationStartSpeed = 0.0;
    private boolean m_decelerationStartSpeedSet = false;
    
    private boolean m_startSpeedSet = false;
    
    private boolean m_SlowDrive = false;
    private boolean m_FastDrive = false;

//    private double m_leftEncoderPosition = 0.0;
//    private double m_rightEncoderPosition = 0.0;
    private double m_targetDistance = 0.0;
    private double m_tolerance = 5.0;
    private double m_output = 0.0;
    private double m_currentDistance = 0.0;
    
    private final double m_approachSpeed = 0.7;
	private final double[] m_crossSpeed = {0.7, 0.7, 0.7, 0.7, 1.0};
//	private final double m_backupSpeed = -0.2;
	private Timer m_crossTimer;
    
	private final double[] AnglesToTurn = { 42.0, 21.5, -10.0, -15.0, -26.0, 180.0 }; //negated all of the angles

    double startingHeading;
    double startingPitch;
    public obstacleDriveState obstacleState = obstacleDriveState.APPROACHING;

//    private final double m_MaxTurnRateLowGear = 359.85;
//    private final double m_MaxTurnRateHighGear = 359.85;// TODO check this rate

    private double m_turnTarget = 0.0;
    private boolean m_turnTargetSet = false;
    private double m_leftMotorValue = 0.0;
    private double m_rightMotorValue = 0.0;
    
    public static final double[] DistanceToDrive =
        { 105, 113.5, 27.5, 27.5, 63.7 };

    public enum driveDistance
    {
        FIRSTDISTANCE, SECONDDISTANCE, THIRDDISTANCE, FOURTHDISTANCE, FITHDISTANCE
    }
    
    private enum distanceStates {
    	  ACCELERATING, 
    	  COASTING, 
    	  DECELERATING, 
    	  STOPPED
    }
    private distanceStates currentState = distanceStates.ACCELERATING;
    
    public enum obstacleDriveState
    {
        STOPPED, 
        APPROACHING, 
        ASCENDING,
        BACKUP, 
        DONE, 
        CROSS, 
        DECENDING
    }
    
	public enum autonAngles
	{
	        FIRSTPOSITION, 
	        SECONDPOSITION, 
	        THIRDPOSITION, 
	        FOURTHPOSITION, 
	        FIFTHPOSITION, 
	        TURN180
	}
	   
	public enum Defense
	{
	   LOWBAR,
	   MOAT,
	   ROCKWALL,
	   ROUGHTERRAIN,
	   RAMPARTS
	}

    /**
     * Initialize the motors for the the robot drive.
     */
    public Drive()
    {	
//    	SmartDashboard.putNumber("angle p", 0.05);
//    	SmartDashboard.putNumber("angle i", 0.005);
//    	SmartDashboard.putNumber("angle d", -0.008);
//    	
//    	SmartDashboard.putNumber("targeting p", 0);
//        SmartDashboard.putNumber("targeting i", 0);
//        SmartDashboard.putNumber("targeting d", 0);
//    	
//    	SmartDashboard.putNumber("v p", 0);
//        SmartDashboard.putNumber("v i", 0);
//        SmartDashboard.putNumber("v d", 0);
//        
//        SmartDashboard.putNumber("Decel range", 0);
    	
        m_leftMotor = new CANTalon(RobotMap.DRIVE_MOTOR_LEFT_MAIN);
        m_rightMotor = new CANTalon(RobotMap.DRIVE_MOTOR_RIGHT_MAIN);
        m_leftMotorB = new CANTalon(RobotMap.DRIVE_MOTOR_LEFT_SLAVE);
        m_rightMotorB = new CANTalon(RobotMap.DRIVE_MOTOR_RIGHT_SLAVE);
        m_leftMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        m_rightMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        m_leftMotor.setInverted(true);
        m_leftMotorB.setInverted(true);
        m_rightMotor.setInverted(true);
        m_rightMotorB.setInverted(true);
        m_drive = new RobotDrive(m_leftMotor, m_leftMotorB, m_rightMotor, m_rightMotorB);

        m_TurningController = new PIDController(0.0, 0.0, 0.0);
        m_TargetingController = new PIDController(0.0,0.0,0.0);//0.002, 0.0004, -0.0007);
        m_LPIDController = new PIDController(0.002, 0.00003, 0.00001);
        m_RPIDController = new PIDController(0.002, 0.00003, 0.0);
//        m_DistanceController = new PIDController(0.0, 0.0, 0.0);
        
        m_crossTimer = new Timer();
        
        SmartDashboard.putNumber("Deceleration range", 0);//TODO remove
    }

    /**
	 * this method can be used to move the robot straight (positive is forward, negative is backward)
	 * 
	 * @param targetVelocity
	 *            desired velocity for the robot in inches per second (positive is forward and 
	 *            negative is backward)
	 */
	public void driveAtSpeed(double targetVelocity)
	{
	    double leftPIDCorrection = (m_LPIDController.calculateOutput((getEncoderVelocity()[0]), targetVelocity));
	    double rightPIDCorrection = (m_RPIDController.calculateOutput((getEncoderVelocity()[1]), targetVelocity));
	
	    //This takes the PID output and adds it to the current motor value
	
	    m_leftMotorValue = (m_leftMotorValue + leftPIDCorrection);
	    m_rightMotorValue = (m_rightMotorValue + rightPIDCorrection);
	
	    //this uses the makeInRange function to keep the value between -1 and 1
	    m_leftMotorValue = (makeInRange(m_leftMotorValue));
	    m_rightMotorValue = (makeInRange(m_rightMotorValue));
	
	    m_drive.tankDrive(m_leftMotorValue, m_rightMotorValue);
	}
	
	public void setApproach()
	{
	    obstacleState = obstacleDriveState.APPROACHING;
	}
	
	/**
	 * driveOverObstacle -- auton code that theoretically crosses all the obstacles
	 * 
	 * @param obstacle - defense to cross
	 */
	public void driveOverObstacle(String obstacle) 
	{
		double pitch = NavXStuffs.getPitch();
		
		switch(obstacleState)
		{
		  case APPROACHING:
			m_crossTimer.reset();
		    
		    m_drive.tankDrive(m_approachSpeed, m_approachSpeed);
		    if(MathCompares.isGreaterThan(pitch, RobotMap.LEVEL_ANGLE, 1))
		    {
		    	obstacleState = obstacleDriveState.ASCENDING;
		    }
			break;
			
	      case ASCENDING:
			m_crossTimer.start();
			obstacleState = obstacleDriveState.CROSS;
			break;
			
		  case CROSS:
			switch(obstacle.toLowerCase())//TODO timer values in RobotMap
			{
				case"lowbar": //TODO determine whether or not to make low bar just straight
					m_drive.tankDrive(m_crossSpeed[0], m_crossSpeed[0]);
					if (m_crossTimer.get() > 1.5 && MathCompares.isEqual(NavXStuffs.getPitch(), RobotMap.LEVEL_ANGLE, 3))
					{
					    m_crossTimer.stop();
                        m_crossTimer.reset();
                        m_crossTimer.start();
						obstacleState = obstacleDriveState.DONE;
					}
				break;
				
				case"roughterrain":
					m_drive.tankDrive(m_crossSpeed[1], m_crossSpeed[1]);
					if (m_crossTimer.get() > 3.0)
					{
					    
					    m_crossTimer.stop();
                        m_crossTimer.reset();
                        m_crossTimer.start();
						obstacleState = obstacleDriveState.DONE;
					}
				break;
				
				case"moat":
					m_drive.tankDrive(m_crossSpeed[2], m_crossSpeed[2]);
					if (m_crossTimer.get() > 4.0)
					{
					    m_crossTimer.stop();
                        m_crossTimer.reset();
                        m_crossTimer.start();
						obstacleState = obstacleDriveState.DONE;
					}
				break;
				
				case"rockwall"://TODO use roll?
					m_drive.tankDrive(m_crossSpeed[3], m_crossSpeed[3]);
					if(MathCompares.isLessThan(NavXStuffs.getPitch(), -3, 1))
					{
					    m_crossTimer.stop();
                        m_crossTimer.reset();
                        m_crossTimer.start();
                        obstacleState = obstacleDriveState.DECENDING;
					}
//					if (m_crossTimer.get() > 2.0)
//					{
//						m_crossTimer.stop();
//						m_crossTimer.reset();
//						m_crossTimer.start();
//						obstacleState = obstacleDriveState.DECENDING;
//					}
				break;
				
				case"ramparts":
					m_drive.tankDrive(m_crossSpeed[4], m_crossSpeed[4]);
					if (m_crossTimer.get() > 1.5)
					{
					    m_crossTimer.stop();
                        m_crossTimer.reset();
                        m_crossTimer.start();
						obstacleState = obstacleDriveState.DONE;
					}
				break;
				
				default:
				break;
			}
			break;
		  case DECENDING:
		      m_drive.tankDrive(0.6, 0.6);
		      if(m_crossTimer.get() > 1.0)
		      {
		          obstacleState = obstacleDriveState.DONE;
		      }
			break;
			
		  case BACKUP:
			  obstacleState = obstacleDriveState.DONE;
			  break;                                      // JW - add missing break
		
		  case DONE://TODO straighten: before or after backup?
			obstacleState = obstacleDriveState.STOPPED;
			break;
			
		  case STOPPED:
			  m_drive.tankDrive(0.0, 0.0);
			break;
			
		  default:
			break;
		}
	}

	/**
     * drive to a distance with trapezoids
     * 
     * @param distance
     */
    //TODO SHORTEN NAME
    public void driveToDistanceAndKeepHeadingVelocityControl(double distance, double startSpeed, double endSpeed, double maxSpeed)//TODO fix the equation for the deceleration breach
    {
        
       //  double m_decelerationRange = 63.255/(1+(9.817*Math.pow(Math.E, -0.0398*distance)));
        double m_decelerationRange = 0;
//        if (Math.abs(distance) < 14.652)
//        {
//            m_decelerationRange = 14.862;
//        }
//        else if (Math.abs(distance) < 96)
//        {
//            m_decelerationRange = (0.00694 * (Math.pow(distance, 2)) - 0.20337 * distance + 16.35229);
//        }
//        else
//        {
//            m_decelerationRange = 62;
//        }
        if(!m_startSpeedSet)
        {
            m_output = startSpeed;
            m_startSpeedSet = true;
        }
//        m_decelerationRange = SmartDashboard.getNumber("Decel range");
        m_decelerationRange = Math.abs(0.505 * distance + 8.24);//* 1.305 * distance + 8.24;//SmartDashboard.getNumber("Deceleration range");//TODO fix to be an equation
        
//      if(distance < 0)//TODO test to see if this fixes negative target distances
//      {
//          m_decelerationRange *= Math.sqrt(2);
//      }
        
        double angleCorrection = 0;
        m_currentDistance = getEncoderPosition()[2];
        m_targetDistance = distance;
    
//        m_TurningController.setPIDConstants(SmartDashboard.getNumber("angle p"), SmartDashboard.getNumber("angle i"), SmartDashboard.getNumber("angle d"));
    
        if(!m_isTargetHeadingSet)
        {
            m_targetHeading = NavXStuffs.getAngle();
        }
        
        angleCorrection = m_TurningController.calculateOutput(NavXStuffs.getAngle(), m_targetHeading);
        
//        SmartDashboard.putNumber("Slope", 1/-m_decelerationRange);
//        SmartDashboard.putNumber("x", m_targetDistance-m_currentDistance);
           
       switch (currentState) {
           case ACCELERATING:
               
               m_decelerationStartSpeedSet = false;
               
//               SmartDashboard.putString("Distance Driving State", "accelerating");
             if (Math.abs(m_output) < maxSpeed) {
               m_output += (distance > 0) ? 0.1 : -0.1;
               currentState = distanceStates.ACCELERATING;
             }
             else if (Math.abs(m_currentDistance-m_targetDistance) < m_decelerationRange) {
               currentState = distanceStates.DECELERATING;
             }
             else if (Math.abs(m_currentDistance) > Math.abs(m_targetDistance))
             {
//                 SmartDashboard.putBoolean("Accelerating past target", true);
                 currentState = distanceStates.DECELERATING;
             }
             else {
               currentState = distanceStates.COASTING;
             }
             break;
             
             
           case COASTING:
               
//               SmartDashboard.putString("Distance Driving State", "coasting");
    
               if(endSpeed != 0)
               {
                   currentState = distanceStates.COASTING;
               }
               else
               {
                   if (Math.abs(m_currentDistance-m_targetDistance) < m_decelerationRange) {
                   currentState = distanceStates.DECELERATING;
                   }
                   else{
                       currentState = distanceStates.COASTING;
                   }
               }
               
               break;
             
             
           case DECELERATING:
               
               if(!m_decelerationStartSpeedSet) 
               {
                   m_decelerationStartSpeed = Math.abs(m_output);
                   m_decelerationStartSpeedSet = true;
               }
               if(endSpeed != 0)
               {
                   currentState = distanceStates.COASTING;
               }
               else
               {
                   if (Math.abs(m_currentDistance-m_targetDistance)< m_tolerance )
                   {//&& Math.abs(getEncoderVelocity()[0]) < 5) { 
                       currentState = distanceStates.STOPPED;
                   }
                   else 
                   {
                       m_output = (m_decelerationStartSpeed/m_decelerationRange)*(m_targetDistance-m_currentDistance);
                       currentState = distanceStates.DECELERATING;
                   }
               }
//               SmartDashboard.putString("Distance Driving State", "decelerating");
    
               break;
                  
               case STOPPED:
//                   SmartDashboard.putString("Distance Driving State", "stopped");
    
                   m_output = 0;
                   break;
      }
       
//      if(distance < 0)
//      {
//          m_output *= -1;
//      }
    
//      SmartDashboard.putNumber("Motor Output", makeInRange(m_output + angleCorrection)); 
//      SmartDashboard.putNumber("Deceleration Range", m_decelerationRange);
      
      //m_LPIDController.setPIDConstants(SmartDashboard.getNumber("v p"), SmartDashboard.getNumber("v i"), SmartDashboard.getNumber("v d"));
      double speedCorrection = m_LPIDController.calculateOutput(getEncoderVelocity()[2], m_output * 201.662);
      
//      SmartDashboard.putNumber("Speed Correction", speedCorrection);
      //m_drive.tankDrive(makeInRange(m_output - angleCorrection)/4, makeInRange(m_output + angleCorrection)/4);
      m_drive.tankDrive(makeInRange(m_output + speedCorrection), makeInRange(m_output + speedCorrection));
    }

	/**
	 * Use this to drive to face a target
	 * 
	 * @param position			where the target is on the camera
	 * @param targetPosition	where the target should be on the camera
	 */
	public void faceTarget(int position, int targetPosition)//TODO zesty testy tests
	{
//	    m_TargetingController.setPIDConstants(SmartDashboard.getNumber("targeting p"),
//        SmartDashboard.getNumber("targeting i"),
//        SmartDashboard.getNumber("targeting d"));
//		double output = m_TargetingController.calculateOutput(position, targetPosition);
//		teleopArcadeDrive(0.5, output);//m_drive.tankDrive(-output, output);//TODO change forward to 0
	}

	/**
     * Returns an array of encoder position values, converted to inches.
     * [0] = left
     * [1] = right
     * [2] = average of both
     * @return   double  - encoder position in inches
     */
    public double[] getEncoderPosition()
    {
        // creates an array of encoder position values
        // 0 = left front
        // 1 = right motor
        // 2 = average of left and right
        double[] encoderPositionArray = new double[3];
        //TODO make some constants
        encoderPositionArray[0] = -((m_leftMotor.getEncPosition()/RobotMap.ENCODER_REVOLUTION_COUNTS)*RobotMap.INCHES_PER_REVOLUTION) * 2.0557;
        encoderPositionArray[1] = ((m_rightMotor.getEncPosition()/RobotMap.ENCODER_REVOLUTION_COUNTS)*RobotMap.INCHES_PER_REVOLUTION) * 2.0557;
        encoderPositionArray[2] = -((((m_leftMotor.getEncPosition()+-m_rightMotor.getEncPosition())/2)/RobotMap.ENCODER_REVOLUTION_COUNTS)*RobotMap.INCHES_PER_REVOLUTION) * 2.0557;
        return encoderPositionArray;
        
    }

    /**
     * Returns an array of encoder velocity values, converted to inches per seconds.
     * [0]=left
     * [1] = right
     * [2] = average of both
     * @return   double  - encoder velocity in inches per second
     */
    public double[] getEncoderVelocity()
    {
        // creates an array of encoder velocity values
        // 0 = left front
        // 1 = right motor
        // 2 = average of left and right
        double[] encoderVelocityArray = new double[3];
        //TODO make some constants
        encoderVelocityArray[0] = -(((m_leftMotor.getEncVelocity()/RobotMap.ENCODER_REVOLUTION_COUNTS)*RobotMap.INCHES_PER_REVOLUTION)) * 10 * 2.0557;
        encoderVelocityArray[1] = (((m_rightMotor.getEncVelocity()/RobotMap.ENCODER_REVOLUTION_COUNTS)*RobotMap.INCHES_PER_REVOLUTION)) * 10 * 2.0557;
        encoderVelocityArray[2] = -(((((m_leftMotor.getEncVelocity()-m_rightMotor.getEncVelocity())/2)/RobotMap.ENCODER_REVOLUTION_COUNTS)*RobotMap.INCHES_PER_REVOLUTION)) * 10 * 2.0557;
        return encoderVelocityArray;
    }

	/**
	 * this method can be used to test motors individually
	 * 
	 * @param left
	              output for left motors
	 * @param right
	 *            output for right motors
	 */
	public void individualMotor(double left, double right)
	{
	  m_drive.tankDrive(left, right);
	}

	/**
	 * {@inheritDoc}
	 */
	public void initDefaultCommand()
	{
		setDefaultCommand(CommandFactory.getCommandFactory().getCommand("cubedarcade"));
	}

	/**
	 * are we done driving distances?
	 * 
	 * @param distance distance to drive
	 * @param tolerance tolerance used to determine if the robot is at the distance
	 * @return true = at the position, false = not there yet
	 */
	public boolean isAtDistanceWithSingleEncoder(double distance, double tolerance)
	{
	    boolean done = false;
	    
	    if(distance < 0)
	    {
	        done = MathCompares.isLessThanOrEqual(getEncoderPosition()[2], distance, tolerance);
	    }
	    else
	    {
	        done = MathCompares.isGreaterThanOrEqual(getEncoderPosition()[2], distance, tolerance);
	    }
		return done;//(Math.abs(getEncoderPosition()[0] - distance) < tolerance);
	}

	/**
	 * Are you at the right angle?
	 * angle that is currently stored by m_turnTarget
	 * 
	 * @return  true = at the angle, false, not there yet
	 */
	public boolean isAtTargetAngle(double tolerance)
	{
		boolean atTarget = (AngularMath.getBestRotationDescriptor(NavXStuffs.getAngle(),
				AngularMath.add(m_turnTarget, 1))[0] < tolerance
				&& AngularMath.getBestRotationDescriptor(NavXStuffs.getAngle(),
						AngularMath.subtract(m_turnTarget, 1))[0] < tolerance);
	
		if (atTarget)
		{
			m_turnTargetSet = false;
		}
		return atTarget;
	}

	/**
	 * Use this to figure out whether the robot is facing towards the target
	 * 
	 * @param target	the x-position that the target should be at
	 * @return			is the target in the correct position?
	 */
	public boolean isFacingTarget(int target)
	{
		return (Math.abs(Camera.getCamera().getX() - target) < 7);
	}

	/**
	 * This accepts values, and if they exceed 1, it sets it to 1.
	 * If input is less than -1, it returns -1.
	 * Otherwise, it returns the unaltered input
	 * @param target
	 *        input value
	 * @return the value to use
	 */
	private double makeInRange(double target)
	{
	    double output = target;
	    double maxOutput = 0;
	    
	    if(m_SlowDrive) maxOutput = 0.4;
	    else if (m_FastDrive)maxOutput = 0.7;
	    else maxOutput = 0.55;
	    
	    if (output > maxOutput)
	    {
	        output = maxOutput;
	    }
	    else if (output < -maxOutput)
	    {
	        output = -maxOutput;
	    }
	
	    return output;
	}

	/**
	 * When this is called, it sets the encoder position values to 0
	 */
	public void resetEncoders()
	{
	    m_leftMotor.setEncPosition(0);
	    m_rightMotor.setEncPosition(0);
	}

	/**
	 * resetTarget -- reset target
	 */
	public void resetTarget()
	{
		m_turnTargetSet = false;
	}

	/**
	 * set distanceState to ACCELERATING
	 */
	public void resetVelocityStateForDistanceDriving()
	{
		currentState = distanceStates.ACCELERATING;
		m_startSpeedSet = false;
	}

	/**
	 * When this is called, it sets the talon brakes
	 * @param brake
	 *        true sets brake, false disables brake
	 */
	public void setBrake(boolean brake)
	{
	    m_leftMotor.enableBrakeMode(brake);
	    m_leftMotorB.enableBrakeMode(brake);
	    m_rightMotor.enableBrakeMode(brake);
	    m_rightMotorB.enableBrakeMode(brake);
	}
	
	
	/**
	 * Slow detailed control
	 * 
	 * @param slow
	 */
	public void setSlowMode(boolean slow)
	{
	    m_SlowDrive = slow;
	}
	
	public void setFastMode(boolean fast)
	{
	    m_FastDrive = fast;
	}

	/**
	 * Stop the robot from driving.
	 * This sets all of the motor outputs to zero
	 */
	public void stop()
	{
		m_drive.arcadeDrive(0.0, 0.0);
	}

	/**
     * Drive the robot with one value being the forward/backward motion and the other being the rotation value. This controls the robot like an video
     * game.
     * 
     * @param moveValue
     *            value for moving the robot forward
     * @param rotateValue
     *            value for rotating the robot
     */
    public void teleopArcadeDrive(double moveValue, double rotateValue)
    {
        double rotateOutput;
        if (Math.abs(rotateValue) < 0.1)
        {
            rotateOutput = 0.0;
        }
        else
        {
            rotateOutput = rotateValue;
        }

//        SmartDashboard.putNumber("Teleop Arcade move value", moveValue);
//        SmartDashboard.putNumber("Teleop Arcade rotate value", makeInRange(rotateValue));
        
        m_drive.arcadeDrive(moveValue, makeInRange(rotateOutput));
    }

    /**
     * Drive the robot by providing the forward/backward value for each side of the robot. Turning is achieved by providing different values to each
     * side.
     * 
     * @param leftValue
     *            speed of the left side of the robot
     * @param rightValue
     *            speed of the right side of the robot
     */
    public void teleopTankDrive(double leftValue, double rightValue)
    {
//    	SmartDashboard.putNumber("Left Motor Value Tank", leftValue);
//    	SmartDashboard.putNumber("Right Motor Value Tank", rightValue);
    	
        m_drive.tankDrive(leftValue, rightValue);
    }
    
    /**
     * turns to an angle
     * 
     * @param Positions the position on the field to shoot from
     */
    public void turnToAngle(autonAngles Positions)
	{
		//m_TurningController.setPIDConstants(SmartDashboard.getNumber("angle p"), SmartDashboard.getNumber("angle i"), SmartDashboard.getNumber("angle d"));
		
		double output;
		
		if (!m_turnTargetSet)
		{
//		    SmartDashboard.putBoolean("got to set target", true);
			m_turnTarget = AngularMath.add(NavXStuffs.getAngle(), AnglesToTurn[Positions.ordinal()]);
			m_turnTargetSet = true;
		}
//		SmartDashboard.putBoolean("targetset", m_turnTargetSet);//TODO Exterminate smrtdshbrd
//		SmartDashboard.putNumber("TurningAngle", m_turnTarget);

		output = m_TurningController.calculateOutput(0,
				AngularMath.getBestRotationDescriptor(NavXStuffs.getAngle(), m_turnTarget)[0]
						* AngularMath.getBestRotationDescriptor(NavXStuffs.getAngle(), m_turnTarget)[1]);

//		SmartDashboard.putNumber("Error", m_TurningController.getError());
		m_drive.tankDrive(output, -output);
//		SmartDashboard.putNumber("Output", output);
//		SmartDashboard.putNumber("RobotAngle", NavXStuffs.getAngle());
	}

	/**
	 * Updates the default command on this subsystem
	 * 
	 * @param command
	 *            the command to run by default
	 */
	public void updateDefaultCommand(Command command)
	{
	    setDefaultCommand(command);
	}
	
	public boolean robotMoving()
    {
        boolean moving = false;
        if (getEncoderVelocity()[2] > 5)
        {
            moving = true;
        }
        else
        {
            moving = false;
        }
        return moving;
    }
    
    public void dashboardInformation()
    {
//        SmartDashboard.putNumber("Robot Speed:", getEncoderVelocity()[2]);
//        SmartDashboard.putNumber("Robot Pitch", NavXStuffs.getPitch());
//        SmartDashboard.putNumber("Robot Roll", NavXStuffs.getRoll());
//        SmartDashboard.putBoolean("Is Robot Moving:", robotMoving());
    }
}