package org.usfirst.frc.team302.robot.utilities;

import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * @version 2 -- Sasquatch      deleted the less important stuff....
 * 
 * @author Derek
 *
 */
public class SmartDashboardData 

{
    public static void displayDashboardData()
    {        
        if (DriverStation.getInstance().getMatchTime()<=20)
        {
            SmartDashboard.putString("Can Arm Be Raised?","Yes");
        }
        else
        {
            SmartDashboard.putString("Can Arm Be Raised?", "No");
        }
        SubsystemFactory.getSubsystemFactory().getDrive().dashboardInformation();
        SubsystemFactory.getSubsystemFactory().getArm().dashboardInformation();
    }
}
