package org.usfirst.frc.team302.robot.commands;

import org.usfirst.frc.team302.robot.subsystems.Arm.ControlStyle;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandFactory
{
    private static CommandFactory m_factory;

    public CommandFactory()
    {

    }

    public static CommandFactory getCommandFactory()
    {
        if (m_factory == null)
        {
            m_factory = new CommandFactory();
        }

        return m_factory;
    }

    /**
     * getCommand - Get and create the command based on the commandID
     * 
     * @param commandID
     *            = name of the command from the text file
     * @return command = the command that was selected
     */
    public Command getCommand(String commandID)
    {
        Command command = null;
        switch (commandID)
        {
        case "cubedarcade":
            command = new CubedArcadeDrive();
            break;
        case "cubedtank":
            command = new CubedTankDrive();
            break;
        case "lineararcade":
            command = new LinearArcadeDrive();
            break;
        case "lineartank":
            command = new LinearTankDrive();
            break;
        case "chevaldefrisedown":
            command = new GoToChevalDeFriseDownPosition();
            break;
        case "chevaldefrisestart":
            command = new GoToChevalDeFriseStartPosition();
            break;
        case "climbend":
            command = new GoToClimbEndPosition();
            break;
        case "climbhook":
            command = new GoToClimbHookPosition();
            break;
        case "climb":
            command = new GoToClimbPosition();
            break;
        case "drawbridgeend":
            command = new GoToDrawbridgeEndPosition();
            break;
        case "drawbridgehook":
            command = new GoToDrawbridgeHookPosition();
            break;
        case "drawbridgestart":
            command = new GoToDrawbridgeEndPosition();
            break;
        case "extendedtopreparetoclimb":
            command = new ExtendToPrepareToClimb();
            break;
        case "portcullisend":
            command = new GoToPortcullisEndPosition();
            break;
        case "portcullisstart":
            command = new PortcullisStart();
            break;
        case "sallyportendhook":
            command = new GoToSallyPortEndHookPosition();
            break;
        case "sallyportstart":
            command = new GoToSallyPortStartPosition();
            break;
        case "startingposition":
            command = new GoToStartingPosition();
            break;
        case "driveslowmode":
            command = new DriveSlowMode();
            break;
        case "drivefastmode":
            command = new DriveFastMode();
            break;
        case "manualarmcontrol":
            command = new SwitchArmControlMode(ControlStyle.MANUAL);
            break;
        case "setpointarmcontrol":
            command = new SwitchArmControlMode(ControlStyle.SET_POINT);
            break;
        case "stoparm":
            command = new StopArm();
            break;
        case "holdposition":
            command = new HoldPosition();
            break;
        case "switcharmmode":
            command = new SwitchArmMode();
            break;
        case "drivemediummode":
            command = new DriveMediumMode();
            break;
        case "underlowbar":
            command = new GoToUnderLowBarPosition();
            break;
        case "preparetoclimbgroup":
            command = new PrepareToClimbGroup();
            break;
        case "ClimbGroup":
            command = new ClimbGroup();
            break;
        default:
        	SmartDashboard.putString("BadCommandChoice", commandID);
        	command = null;
            break;
        }
        return command;
    }
}
