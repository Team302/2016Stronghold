package org.usfirst.frc.team302.robot.utilities;

public class Actuator
{

    private static final double m_MaximumShoulderAngle = 90.0;//90 - 50
    private static final double m_MinimumShoulderAngle = 22.0;//20 - 30
    
    private static final double m_RampDownShoulderExtended = 82.0;//82 - 35
    private static final double m_RampDownShoulderRetracted = 28.0;//28 - 45
    
    private static final double m_MaximumElbowAngle = 129.0;//126 - 80
    private static final double m_MinimumElbowAngle = 15.0;//4 - 30
    
    private static final double m_RampDownElbowExtended = 125.0;//118 - 60
    private static final double m_RampDownElbowRetracted = 25.0;//12 - 50
    
    public static final Actuator shoulder = new Actuator(m_MaximumShoulderAngle, m_MinimumShoulderAngle, m_RampDownShoulderRetracted, m_RampDownShoulderExtended);
    public static final Actuator elbow = new Actuator(m_MaximumElbowAngle, m_MinimumElbowAngle, m_RampDownElbowRetracted, m_RampDownElbowExtended);
    
    private static double m_MaximumAngle;
    private static double m_MinimumAngle;
    
    private static double m_RampDownExtended;
    private static double m_RampDownRetracted;
    
    public enum linearActuator
    {
        SHOULDER,
        ELBOW
    }
    
    private Actuator(double maximumAngle, double minimumAngle, double rampDownRetracted, double rampDownExtended) {
        m_MaximumAngle = maximumAngle;
        m_MinimumAngle = minimumAngle;
        m_RampDownRetracted = rampDownRetracted;
        m_RampDownExtended = rampDownExtended;
    }
    
    public static double getMaximumAngle() 
    {
        return m_MaximumAngle;
    }
    
    public static double getMinimumAngle()
    {
        return m_MinimumAngle;
    }
    
    public static double getRampDownExtended()
    {
        return m_RampDownExtended;
    }
    
    public static double getRampDownRetracted()
    {
        return m_RampDownRetracted;
    }
    
    public static Actuator getActuator(linearActuator actuator)
    {
        Actuator choice = null;
        
        switch(actuator)
        {
            case SHOULDER:
                choice = shoulder;
                break;
            case ELBOW:
                choice = elbow;
                break;
            default:
                break;
        }
        
        return choice;
    }
    
}
