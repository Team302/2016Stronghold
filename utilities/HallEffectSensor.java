package org.usfirst.frc.team302.robot.utilities;

import edu.wpi.first.wpilibj.AnalogInput;


/**
 * This is a class to work with hall effect sensors
 * 
 * <h1>Change Log: </h1>
 * 
 * @version <b>version 1:</b>
 * 			<p>
 * 				2/22/2016 --Derek Witcpalek --> Original implementation of hall effect sensor
 * 			</p>
 * 
 * @author Derek
 *
 */
public class HallEffectSensor implements IAngleSensor {
	
	AnalogInput sensor;
	double m_zeroAngleOffset;
	
	
	/**
	 * Creator
	 * 
	 * @param channel	where is the sensor plugged in?
	 * @param zeroAngleOffset what is the angle read from the sensor that should be set to zero
	 */
	public HallEffectSensor(int channel, double zeroAngleOffset)
	{
	    m_zeroAngleOffset = zeroAngleOffset;
		sensor = new AnalogInput(channel);
	}
	
	
	/**
	 * Get the current angle of the hall effect sensor
	 * 
	 * @return	angle measured by the sensor
	 */
	public double getAngle()
	{
	    return (180 - ((90000 / 2213) * (sensor.getVoltage()-0.189) - m_zeroAngleOffset));
//		return ((4.5-sensor.getVoltage() * (101.25/4.5))); 
//		return ((180/4.6) * (sensor.getVoltage()-0.2));
	}
}
