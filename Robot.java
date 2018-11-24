
package org.usfirst.frc.team302.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team302.robot.commands.AutonActions;
import org.usfirst.frc.team302.robot.commands.CrossLowBarAuton;
import org.usfirst.frc.team302.robot.commands.CrossObstacle;
import org.usfirst.frc.team302.robot.commands.CrossPortcullisAuton;
import org.usfirst.frc.team302.robot.commands.GoToStartingPosition;
import org.usfirst.frc.team302.robot.commands.GoToUnderLowBarPosition;
import org.usfirst.frc.team302.robot.commands.NoMove;
import org.usfirst.frc.team302.robot.commands.RampartsAutonGroup;
import org.usfirst.frc.team302.robot.commands.SwitchArmControlMode;
import org.usfirst.frc.team302.robot.subsystems.Arm.ControlStyle;
import org.usfirst.frc.team302.robot.subsystems.Camera;
import org.usfirst.frc.team302.robot.subsystems.SubsystemFactory;
import org.usfirst.frc.team302.robot.utilities.NavXStuffs;
import org.usfirst.frc.team302.robot.utilities.SmartDashboardData;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is the clean Competition version of Robot.java
 * 
 * @author Josh
 *
 */
public class Robot extends IterativeRobot 
{
	// things to put Interface choices on Dashboard
    final String defaultInterface = "Interface";
    final String joshInterface = "joshInterface";
    
    final String lowbar = "lowbar";
    final String rockwall = "rockwall";
    final String noMove = "nomove";
    final String portcullis = "portcullis";
    final String ramparts = "ramparts";
    final String roughTerrain = "roughterrain";
    
    SelectAutonMode m_SAM;
    AutonActions m_AutonActions;
    String oiSelected;
    SendableChooser m_oiChooser;
    OI m_oi;
    SendableChooser m_Auton;
    
    SendableChooser m_Position;
    
    public void robotInit() 
    {
    	m_oi = OI.getOI();
        m_SAM = new SelectAutonMode();
        
     // putting interface choices on dashboard
        m_oiChooser = new SendableChooser();
        m_oiChooser.addDefault("Default Interface", defaultInterface);
        m_oiChooser.addObject("Josh Interface", joshInterface);
        SmartDashboard.putData("Interfaces", m_oiChooser);
        
        m_Auton = new SendableChooser();
        m_Auton.addDefault(noMove, noMove);
        m_Auton.addObject(lowbar, lowbar);
        m_Auton.addObject(rockwall, rockwall);
        m_Auton.addObject(portcullis, portcullis);
        m_Auton.addObject(ramparts, ramparts);
        m_Auton.addObject(roughTerrain, roughTerrain);
        
//        m_Position = new SendableChooser();
//        m_Position.addDefault("1st position (low bar)", autonAngles.FIRSTPOSITION);
//        m_Position.addObject("2nd position", autonAngles.SECONDPOSITION);
//        m_Position.addObject("3rd position", autonAngles.THIRDPOSITION);
//        m_Position.addObject("4th position", autonAngles.FOURTHPOSITION);
//        m_Position.addObject("5th position", autonAngles.FIFTHPOSITION);
        
        SmartDashboard.putData("Auton", m_Auton);
        
//        m_SAM.getPosition();
//        m_SAM.getAimPosition();
//        m_SAM.getDefense();
//        m_SAM.getDefenseAction();
//        m_SAM.getShootCommand();
//        m_SAM.getMoveAction();
//        m_SAM.getMoveActionSecond();
//        m_SAM.getMoveActionThird();
    }
	
    public void disabledInit(){

    }
	
	public void disabledPeriodic() 
	{
		Scheduler.getInstance().run();
	}

    public void autonomousInit() 
    {
        switch(m_Auton.getSelected().toString().toLowerCase())
        {
        case lowbar:
            new CrossLowBarAuton().start();
            break;
        case rockwall:
            new CrossObstacle("rockwall").start();
            break;
        case noMove:
            new NoMove().start();;
            break;
        case portcullis:
            new CrossPortcullisAuton().start();
            break;
        case ramparts:
            new RampartsAutonGroup().start();
            break;
        case roughTerrain:
            new CrossObstacle("roughterrain").start();
            break;
        default:
            break;
        }
    }

    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    public void teleopInit() 
    {
    	// using the selection
        oiSelected = (String) m_oiChooser.getSelected();
        m_oi.readFile(oiSelected);
        
        SmartDashboard.putData("Go To starting position", new GoToStartingPosition());
        SmartDashboard.putData("Switch to manual arm control", new SwitchArmControlMode(ControlStyle.MANUAL));
        SmartDashboard.putData("Switch to setpoint arm control", new SwitchArmControlMode(ControlStyle.SET_POINT));
    }

    public void teleopPeriodic() 
    {
        Scheduler.getInstance().run();
        //SmartDashboardData.displayDashboardData();
//        Camera.getCamera().getAndSetImage();
        SmartDashboard.putString("Arm Control Mode", SubsystemFactory.getSubsystemFactory().getArm().getControlStyle() + "");
        SmartDashboard.putNumber("Elbow Angle", SubsystemFactory.getSubsystemFactory().getArm().getElbowAngle());
        SmartDashboard.putNumber("Shoulder Angle", SubsystemFactory.getSubsystemFactory().getArm().getShoulderAngle());
    }
}
