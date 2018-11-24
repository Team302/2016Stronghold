package org.usfirst.frc.team302.robot.subsystems;

/**
 * This class contains all of the setpoints for the arm and methods to access each of the
 * specific pieces of information about that setpoint: height, extension, shoulder angle,
 * elbow angle.
 * 
 * <h1>Change Log: </h1>
 * 
 * @version <b>version 1:</b> 3/5/2016 --Derek Witcpalek
 * 			<p>
 * 			Initial implementation to clean up the setpoints from before
 * 			</p>
 * 
 * @author Derek
 *
 */
public class ArmSetpoint {

	private String m_Name;
	private double m_Height;
	private double m_Extension;
	private double m_ShoulderJointAngle;
	private double m_ElbowJointAngle;	
	
	
	
	/*
	 * ======================================================================================================
	 * ~~~~~~~~~~~~~~~~~~~THESE ARE ALL VARIOUS CONSTANT OBJECTS OF THE ARM SETPOINT CLASS~~~~~~~~~~~~~~~~~~~
	 * ======================================================================================================
	 */
	
	private static final ArmSetpoint CHEVAL_DE_FRISE_DOWN_SETPOINT = 
			new ArmSetpoint( 
					"Cheval de frise down",		//name
					1.0141371013307108, 		//height
					10.001163268061898, 		//extension
					55.0, 						//shoulder angle
					15.0 						//elbow angle
			);	
	
    private static final ArmSetpoint DRAWBRIDGE_END_SETPOINT = 
    		new ArmSetpoint( 
    				"Drawbridge end",			//name
    				1.0141371013307108, 		//height
    				10.001163268061898, 		//extension
    				55.0, 						//shoulder angle
    				15.0 						//elbow angle
    		);	
    
    private static final ArmSetpoint PORTCULLIS_START_SETPOINT = 
    		new ArmSetpoint( 
    				"Portcullis start",			//name
    				3.005999671708121, 			//height
    				10.010186260606531, 		//extension
    				57.422, 					//shoulder angle
    				14.936 						//elbow angle
    		);
    
    private static final ArmSetpoint CHEVAL_DE_FRISE_START_SETPOINT = 
    		new ArmSetpoint( 
    				"Cheval de frise start",	//name
    				11.986974792778978, 		//height
    				9.998582217334643, 			//extension
    				10.19999999999998, 			//shoulder angle
    				15.0 			            //elbow angle
    		);	
    
    private static final ArmSetpoint SALLY_PORT_END_HOOK_SETPOINT = 
    		new ArmSetpoint( 
    				"Sally port end hook",		//name
    				23.98490905846637, 			//height
    				10.003140110594842, 		//extension
    				40.0, 						//shoulder angle
    				25.0 							//elbow angle
    		);	
    
    private static final ArmSetpoint DRAWBRIDGE_HOOK_SETPOINT = 
    		new ArmSetpoint( 
    				"Drawbridge end hook",		//name
    				27.974091722725284, 		//height
    				9.993767579922412, 			//extension
    				45.0, 						//shoulder angle
    				46.0 							//elbow angle
    		);	
    
    private static final ArmSetpoint SALLY_PORT_START_SETPOINT = 
    		new ArmSetpoint( 
    				"Sally port start",			//name
    				27.974091722725284, 		//height
    				9.993767579922412, 			//extension
    				35.0, 						//shoulder angle
    				40.0 							//elbow angle
    		);
    
    private static final ArmSetpoint DRAWBRIDGE_START_SETPOINT = 
    		new ArmSetpoint( 
    				"Drawbridge start",			//name
    				36.993079081105925, 		//height
    				9.997517968723486, 			//extension
    				57.422, 					//shoulder angle
    				14.936 					    //elbow angle
    		);
    
    private static final ArmSetpoint PORTCULLIS_END_SETPOINT = 
    		new ArmSetpoint( 
    				"Portcullis end",			//name
    				36.993079081105925, 		//height
    				9.997517968723486, 			//extension
    				23.731, 			        //shoulder angle
    				15.0			            //elbow angle
    		);
    
    private static final ArmSetpoint CLIMB_END_SETPOINT = 
    		new ArmSetpoint( 
    				"Climb end",				//name
    				36.993079081105925, 		//height
    				9.997517968723486, 			//extension
    				35.401, 			        //shoulder angle
    				63.282           			//elbow angle
    		);
    
    private static final ArmSetpoint EXTENDED_TO_PREPARE_TO_CLIMB_SETPOINT = 
    		new ArmSetpoint( 
    				"Extended to prepare to climb",		//name
    				83.00191717396177, 					//height
    				5.015826018141382, 					//extension
    				76.568, 			              	//shoulder angle
    				123.584 				            //elbow angle
    		);
    
    private static final ArmSetpoint CLIMB_HOOK_SETPOINT = 
    		new ArmSetpoint( 
    				"Climb hook",				//name
    				76.00342150938539, 			//height
    				9.980707180825094, 			//extension
    				83.692, 			        //shoulder angle
    				119.434 			        //elbow angle
    		);
    
    private static final ArmSetpoint CLIMB_SETPOINT = 
    		new ArmSetpoint( 
    				"Climb",		//name
    				76.00342150938539, 			//height
    				9.980707180825094, 			//extension
    				73.00000000000013, 			//shoulder angle
    				131.0999999999968 			//elbow angle
    		);
    
    private static final ArmSetpoint STARTING_POSITION_SETPOINT = 
    		new ArmSetpoint( 
    				"Starting position",		//name
    				43.02171180527519, 			//height
    				9.993163342193576, 			//extension
    				26.123, 		            //shoulder angle
    				58.057 			            //elbow angle
    		);
    
    private static final ArmSetpoint UNDER_LOW_BAR_SETPOINT =
            new ArmSetpoint(
                    "Low bar",                  //name
                    0.0,                        //height
                    0.0,                        //extension
                    22.9,                       //shoulder angle
                    15.0                        //elbow angle
            );
    
    private static final ArmSetpoint HIGH_GOAL_SHOT_SETPOINT =
            new ArmSetpoint(
                    "High goal shot",           //name
                    0.0,                        //height
                    0.0,                        //extension,       
                    26.123,                     //shoulder angle
                    58.057                      //elbow angle
            );           
    
    private static final ArmSetpoint P_PORTCULLIS_SETPOINT =
            new ArmSetpoint(
                    "High goal shot",           //name
                    0.0,                        //height
                    0.0,                        //extension,       
                    57.422,                     //shoulder angle
                    14.936                      //elbow angle
            );  
    
	
    /**
     * This is a list of the possible states the arm can go to
     */
    public enum ArmState
    {
        CHEVAL_DE_FRISE_DOWN, 
        DRAWBRIDGE_END, PORTCULLIS_START, 
        CHEVAL_DE_FRISE_START, 
        SALLY_PORT_END_HOOK, 
        DRAWBRIDGE_HOOK, 
        SALLY_PORT_START, 
        DRAWBRIDGE_START, 
        PORTCULLIS_END, 
        CLIMB_END, 
        EXTENDED_TO_PREPARE_TO_CLIMB, 
        CLIMB_HOOK, 
        CLIMB, 
        STARTING_POSITION,
        UNDER_LOW_BAR_POSITION,
        HIGH_GOAL_SHOT_POSITION,
        P_PORTCULLIS_POSITION
    }
    
    
    
    
    
    /*
	 * ======================================================================================================
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~CLASS METHODS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * ======================================================================================================
	 */
    
    
    /**
     * Creator -- you can't call this
     * 
     * ***All of the arm setpoints are pre-created in this class, so there is no need to
     * 	  call this method outside of the creation of those constants
     * 
     * @param name      			what is the name of the setpoint / what do you call it?
     * 
     * @param height				how high is the endpoint of the arm?
     * 
     * @param extension				how far does the arm extend from our frame (not our bumper)?
     * 
     * @param shoulderJointAngle	what angle is the shoulder at -- this is measured as the angle
     * 								between the straight line going between the two pivots and the
     * 								line going straight across the chassis
     * 
     * @param elbowJointAngle		what angle is the elbow at -- this is measured as the angle 
     * 								between the straight line going from pivot to pivot and the 
     * 								straight line going from the second pivot to the center of the
     * 								end effector claw
     */
	private ArmSetpoint(String name, double height, double extension, 
			double shoulderJointAngle, double elbowJointAngle) 
	{
		m_Name = name;
		m_Height = height;
		m_Extension = extension;
		m_ShoulderJointAngle = shoulderJointAngle;
		m_ElbowJointAngle = elbowJointAngle;
	}
	
	
	/**
	 * Use this method to get the elbow angle value for this set point
	 * 
	 * @return	the angle between the straight line going between the two
	 * 			pivots and the straight line going between the second pivot
	 * 			and the center of the end effector claw
	 */
	public double getElbowJointAngle()
	{
		return m_ElbowJointAngle;
	}


	/**
	 * Use this method to get the extension value from the arm set point
	 * 
	 * @return	the extension that this position should put the arm's end
	 * 			effector at -- this is measured from the frame perimeter,
	 * 			not the bumpers
	 */
	public double getExtension()
	{
		return m_Extension;
	}


	/**
	 * Use this method to get the height value from the arm Set Point
	 * 
	 * @return	the height that this position should put the arm's end effector at
	 */
	public double getHeight()
	{
		return m_Height;
	}


	/**
	 * Use this method to get the name of the state
	 * 
	 * @return	name of setpoint state
	 */
	public String getName()
	{
		return m_Name;
	}


	/**
	 * Use this to get one of the arm setpoints
	 * 
	 * @param state		this is one of the set states in the ArmState list of constants
	 * @return	ArmSetpoint		this is the set of data for the position requested (height, extension,
	 * 							shoulder angle, elbow angle)
	 */
	public static ArmSetpoint getPosition(ArmState state)
    {
        ArmSetpoint value = null;
        switch (state)
        {
        case CHEVAL_DE_FRISE_DOWN:
            value = CHEVAL_DE_FRISE_DOWN_SETPOINT;
            break;
            
        case DRAWBRIDGE_END:
            value = DRAWBRIDGE_END_SETPOINT;
            break;
            
        case PORTCULLIS_START:
            value = PORTCULLIS_START_SETPOINT;
            break;
            
        case CHEVAL_DE_FRISE_START:
            value = CHEVAL_DE_FRISE_START_SETPOINT;
            break;
            
        case SALLY_PORT_END_HOOK:
            value = SALLY_PORT_END_HOOK_SETPOINT;
            break;
            
        case DRAWBRIDGE_HOOK:
            value = DRAWBRIDGE_HOOK_SETPOINT;
            break;
            
        case SALLY_PORT_START:
            value = SALLY_PORT_START_SETPOINT;
            break;
            
        case DRAWBRIDGE_START:
            value = DRAWBRIDGE_START_SETPOINT;
            break;
            
        case PORTCULLIS_END:
            value = PORTCULLIS_END_SETPOINT;
            break;
            
        case CLIMB_END:
            value = CLIMB_END_SETPOINT;
            break;
            
        case EXTENDED_TO_PREPARE_TO_CLIMB:
            value = EXTENDED_TO_PREPARE_TO_CLIMB_SETPOINT;
            break;
            
        case CLIMB_HOOK:
            value = CLIMB_HOOK_SETPOINT;
            break;
            
        case CLIMB:
            value = CLIMB_SETPOINT;
            break;
            
        case STARTING_POSITION:
            value = STARTING_POSITION_SETPOINT;
            break;
            
        case UNDER_LOW_BAR_POSITION:
            value = UNDER_LOW_BAR_SETPOINT;
            break;
            
        case HIGH_GOAL_SHOT_POSITION:
            value = HIGH_GOAL_SHOT_SETPOINT;
            break;
            
        case P_PORTCULLIS_POSITION:
            value = P_PORTCULLIS_SETPOINT;
            break;
            
        default:
            value = null;
            break;
        }

        return value;
    }
	
	
	/**
	 * Use this method to get the shoulder angle value for this set point
	 * 
	 * @return	the angle between the robot and the straight line between 
	 * 			the two pivots on the arm
	 */
	public double getShoulderJointAngle()
	{
		return m_ShoulderJointAngle;
	}
	
}
