package org.usfirst.frc.team302.robot.utilities;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class contains all of the functions with the linear actuators. These include: creating
 * the motor for the actuator, setting the default sensors, and ensuring that the actuator does
 * not break due to overextension.
 * 
 * <h1>Change Log: </h1>
 * 
 * @version <b>version 1:</b>
 * 			<p>
 * 				3/5/2016 --Derek Witcpalek -- Class with ramp down working to slow the actuator as it nears
 * 						maximum extension and minimum extension
 * 			</p>
 * 
 * @version <b>version 2:</b>
 * 			<p>
 * 				3/7/2016 --Derek Witcpalek -- The actuator can now be created with a different
 * 						number of potentiometer counts at zero extension
 * 			</p>
 * 
 * @author Derek
 *
 */
public class LinearActuator {

	private CANTalon m_Actuator;
	
	private final double m_MinimumExtension = 0.0;
	private final double m_RampDownZoneRetracted = 2.0;
	
	private final double m_MaximumExtension = 10.5;
	private final double m_RampDownZoneExtended = 9.0;
	
	private final double m_MaximumOutput = 1.0;
	private final double m_MinimumOutput = -1.0;
	
	private final double m_RetractedActuatorLength = 0.0; //TODO fix to actual
	
	private double m_PotentiometerCountsAtZeroExtension;
	
	
	
	/**
	 * Creator: call this to create each linear actuator
	 * 
	 * @param id	this is the CAN id of the motor that you are using in the actuator
	 */
	public LinearActuator(int id) 
	{
		this(id, 206);
	}
	
	
	public LinearActuator(int id, double potentiometerCountsAtZero)
	{
		m_PotentiometerCountsAtZeroExtension = potentiometerCountsAtZero;
		m_Actuator = new CANTalon(id);
		m_Actuator.setInverted(true);
		m_Actuator.setFeedbackDevice(FeedbackDevice.AnalogPot);
//		m_Actuator.enableLimitSwitch(true, true);						//TODO check this one
	}
	
	
	/**
	 * Use this to get the current position of the actuator
	 * 
	 * @return	position	this is the extension of the actuator in inches
	 */
	public double getExtension()
	{
	    return ((m_Actuator.getPosition()) / 74 - m_PotentiometerCountsAtZeroExtension);
//		return ((actuator.getPosition() - m_PotentiometerCountsAtZeroExtension) / 74);
	}
	
	
	/**
	 * Use this to get the current length of the actuator
	 * 
	 * @return	position	this is the entire length of the actuator in inches
	 */
	public double getLength()
	{
		return getExtension() + m_RetractedActuatorLength;
	}
	
	
	/**
	 * Use this method to set the output of the motor.
	 * This method will automatically ramp down the output 
	 * when the actuator gets close to the maximum extension.
	 * Additionally, this class uses the hall effect sensors
	 * to limit automatically stop the actuator when it
	 * reaches a maximum or minimum extension. This will
	 * protect the arm in the case that the potentiometer
	 * breaks and ensure that the actuator still stops.
	 * 
	 * @param output	this is the value the motor should run at
	 */
	public void set(double output)//TODO remove smart dashboard stuff
	{
		double modifiedOutput = output;
		
//		SmartDashboard.putNumber("Unmodified output", output);
		
		if(output < 0.0)
		{
//			SmartDashboard.putString("Target", "Going towards max");
			double rampDownMaximumOutput = -(-m_MaximumOutput / (m_MaximumExtension - m_RampDownZoneExtended) * (getExtension() - m_RampDownZoneExtended) + m_MaximumOutput);
//			SmartDashboard.putNumber("Ramp Down Maximum Value", rampDownMaximumOutput);			
			
			
			if(getExtension() >= m_MaximumExtension) 
			{
//				SmartDashboard.putNumber("Case", 1);
//				SmartDashboard.putBoolean("At Max", true);
				modifiedOutput = 0.0;
			}
			else if(getExtension() > m_RampDownZoneExtended && output < rampDownMaximumOutput)
			{
//				SmartDashboard.putNumber("Case", 2);
//				SmartDashboard.putBoolean("At Max", false);
				modifiedOutput = rampDownMaximumOutput;
			}
			else
			{
//				SmartDashboard.putNumber("Case", 3);
//				SmartDashboard.putBoolean("At Max", false);
				modifiedOutput = output;
			}
		}
		else if(output > 0.0)
		{
//			SmartDashboard.putString("Target", "Going towards min");
			double rampDownMaximumOutput = -(m_MinimumOutput / (m_RampDownZoneRetracted - m_MinimumExtension)
					* (getExtension() - m_MinimumExtension));
//			SmartDashboard.putNumber("Ramp Down Maximum Value", rampDownMaximumOutput);
			
			
			if(getExtension() <= m_MinimumExtension)
			{
//				SmartDashboard.putNumber("Case", 4);
//				SmartDashboard.putBoolean("At Min", true);
				modifiedOutput = 0.0;
			}
			else if(getExtension() < m_RampDownZoneRetracted && output > rampDownMaximumOutput)
			{
//				SmartDashboard.putNumber("Case", 5);
//				SmartDashboard.putBoolean("At Min", false);
				modifiedOutput = rampDownMaximumOutput;
			}
			else
			{
//				SmartDashboard.putNumber("Case", 6);
//				SmartDashboard.putBoolean("At Min", false);
				modifiedOutput = output;
			}
		}
		
//		SmartDashboard.putNumber("Modified Output", modifiedOutput);
		
		
		m_Actuator.set(modifiedOutput);
	}
	
	
	/**
     * Use this method to set the output of the motor.
     * This method will automatically ramp down the output 
     * when the actuator gets close to the maximum extension.
     * Additionally, this class uses the hall effect sensors
     * to limit automatically stop the actuator when it
     * reaches a maximum or minimum extension. This will
     * protect the arm in the case that the potentiometer
     * breaks and ensure that the actuator still stops.
     * 
     * @param output    this is the value the motor should run at
     */
    public void set(double output, double angle, Actuator actuator)//TODO remove smart dashboard stuff
    {
        double modifiedOutput = output;
        
//        SmartDashboard.putNumber("Unmodified output", output);
//        
//        SmartDashboard.putNumber("Target Output", output);
//        SmartDashboard.putNumber("Angle", angle);
//        SmartDashboard.putString("Actuator", actuator + "");
//        
//        SmartDashboard.putNumber("Minimum angle", actuator.getMinimumAngle());
//        SmartDashboard.putNumber("Maximum angle", actuator.getMaximumAngle());
//        SmartDashboard.putNumber("Ramp Down Retract", actuator.getRampDownRetracted());
//        SmartDashboard.putNumber("Ramp Down extend", actuator.getRampDownExtended());
        
        if(output < 0.0)
        {
//            SmartDashboard.putString("Target", "Going towards max");
            double rampDownMaximumOutput = -(-m_MaximumOutput / (actuator.getMaximumAngle() - actuator.getRampDownExtended()) * (angle - actuator.getRampDownExtended()) + m_MaximumOutput);
//            SmartDashboard.putNumber("Ramp Down Maximum Value", rampDownMaximumOutput);         
            
            
            if(angle >= actuator.getMaximumAngle()) 
            {
//                SmartDashboard.putNumber("Case", 1);
//                SmartDashboard.putBoolean("At Max", true);
                modifiedOutput = 0.0;
            }
            else if(angle > actuator.getRampDownExtended() && output < rampDownMaximumOutput)
            {
//                SmartDashboard.putNumber("Case", 2);
//                SmartDashboard.putBoolean("At Max", false);
                modifiedOutput = rampDownMaximumOutput;
            }
            else
            {
//                SmartDashboard.putNumber("Case", 3);
//                SmartDashboard.putBoolean("At Max", false);
                modifiedOutput = output;
            }
        }
        else if(output > 0.0)
        {
//            SmartDashboard.putString("Target", "Going towards min");
            double rampDownMaximumOutput = -(m_MinimumOutput / (actuator.getRampDownRetracted() - actuator.getMinimumAngle())
                    * (angle - actuator.getMinimumAngle()));
//            SmartDashboard.putNumber("Ramp Down Maximum Value", rampDownMaximumOutput);
            
            
            if(angle <= actuator.getMinimumAngle())
            {
//                SmartDashboard.putNumber("Case", 4);
//                SmartDashboard.putBoolean("At Min", true);
                modifiedOutput = 0.0;
            }
            else if(angle < actuator.getRampDownRetracted() && output > rampDownMaximumOutput)
            {
//                SmartDashboard.putNumber("Case", 5);
//                SmartDashboard.putBoolean("At Min", false);
                modifiedOutput = rampDownMaximumOutput;
            }
            else
            {
//                SmartDashboard.putNumber("Case", 6);
//                SmartDashboard.putBoolean("At Min", false);
                modifiedOutput = output;
            }
        }
        
        SmartDashboard.putNumber("Modified Output", modifiedOutput);
        
        
        m_Actuator.set(modifiedOutput);
    }
}
