package org.usfirst.frc.team302.robot;

import org.usfirst.frc.team302.robot.commands.CrossObstacle;
import org.usfirst.frc.team302.robot.commands.DriveToDistanceWithSingleEncoder;
import org.usfirst.frc.team302.robot.commands.NoMove;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class determines the actions to be performed during autonomous mode
 * 
 * @author Josh Baker
 */

//
// Joe Witcpalek - Added additional parameters to new DriveToDistanceWithSingleEncoder
//

public class SelectAutonMode
{
	private SendableChooser m_position;
	private final String p1 = "Position 1";
	private final String p2 = "Position 2";
	private final String p3 = "Position 3";
	private final String p4 = "Position 4";
	private final String p5 = "Position 5";
	
	private SendableChooser m_defenseAction;
	private final String cross = "Cross";
	private final String reach = "Reach";
	private final String cdf = "Cheval De Frise";
	private final String portcullis = "Portcullis";
	private final String sallyPort = "Sally Port";
	private final String noMove = "No Move";
	
	private SendableChooser m_defense;
	private final String lowbar = "Lowbar";
	private final String moat = "Moat";
	private final String ramparts = "Ramparts";
	private final String roughTerrain = "RoughTerrain";
	private final String rockWall = "RockWall";

	private SendableChooser m_move;
	private final String forward = "Forward";
	private final String backward = "Backward";
	private final String turnRight = "Turn Right";
	private final String turnLeft = "Turn Left";
	
	private SendableChooser m_move2;
	private SendableChooser m_move3;
	
	public SelectAutonMode()
	{
		m_defenseAction = new SendableChooser();
		m_defense = new SendableChooser();
		m_position = new SendableChooser(); //TODO WHAT ART THOU?
		m_move = new SendableChooser();
		m_move2 = new SendableChooser();
		m_move3 = new SendableChooser();
	}
	
	/**
	 * getPosition() -- Determines what position we are in 
	 * 					based on a choice on the smartDashboard
	 * @return Selected position
	 */
	public String getPosition()
    {
    	m_position.addDefault(p1, p1);
    	m_position.addObject(p2, p2);
    	m_position.addObject(p3, p3);
    	m_position.addObject(p4, p4);
    	m_position.addObject(p5, p5);
    	SmartDashboard.putData("Position", m_position);
    	return (String) m_position.getSelected();
    }
	
	/**
	 * positionCommand() -- Determines what command to run
	 * 
	 * @return command
	 */
	public Command positionCommand() //TODO WHY DO YOU EXIST?
	{
		String position = getPosition();
		Command command = new NoMove();
		switch(position)
		{
		case p1:
//			command = null;
			break;
		case p2:
//			command = null;
			break;
		case p3:
//			command = null;
			break;
		case p4:
//			command = null;
			break;
		case p5:
//			command = null;
			break;
		default:
//			command = null;
			break;
		}
		return command;
	}

	/**
	 * getDefenseAction() -- Determines what Defense Action we want 
	 * 						 based on a choice on the smartDashboard
	 * @return Selected Defense Action
	 */
    public String getDefenseAction()
    {
    	m_defenseAction.addDefault(noMove, noMove);
    	m_defenseAction.addObject(cross, cross);
    	m_defenseAction.addObject(reach, reach);
    	m_defenseAction.addObject(cdf, cdf);
    	m_defenseAction.addObject(portcullis, portcullis);
    	m_defenseAction.addObject(sallyPort, sallyPort);
    	return (String) m_defenseAction.getSelected();
    }
    
    public String getDefense()
    {
        m_defense.addDefault(lowbar, lowbar);
        m_defense.addObject(moat, moat);
        m_defense.addObject(ramparts, ramparts);
        m_defense.addObject(roughTerrain, roughTerrain);
        m_defense.addObject(rockWall, rockWall);
        return (String) m_defense.getSelected();
    }
    
	/**
	 * defenseCommand() -- Determines what command to run
	 * 
	 * @return command
	 */
    public Command defenseActionCommand()
    {
    	String defenseAction = getDefenseAction();
    	String defense = getDefense();
    	Command command = null;
    	
    	switch(defenseAction)
    	{
    	case noMove:
    		command = new NoMove();
    		break;
    	case cross:
    	    switch(defense)
            {
    	    case noMove:
    	        command = new NoMove();
    	        break;
            case lowbar:
                command = new CrossObstacle("lowbar");
                break;
            case moat:
                command = new CrossObstacle("moat");
                break;
            case ramparts:
                command = new CrossObstacle("ramparts");
                break;
            case rockWall:
                command = new CrossObstacle("rockwall");
                break;
            case roughTerrain:
                command = new CrossObstacle("roughterrain");
                break;
            default:
                command = new NoMove();
                break;
            }
    		break;
//    	case reach:
//    		command = null; //TODO WHY CAN'T WE HAVE THIS?
//    		break;
//    	case cdf:
//    		command = null;
//    		break;
//    	case portcullis:
//    		command = null;
//    		break;
//    	case sallyPort:
//    		command = null;
//    		break;
    	default:
            command = new NoMove();
    		break;
    	}
    	return command;
    }

	/**
	 * getMoveAction() -- Determines what Move Action we want 
	 * 					  based on a choice on the smartDashboard
	 * @return Selected Move Action
	 */
    public String getMoveAction()
    {
    	m_move.addDefault("Move 1", noMove);
    	m_move.addObject(forward, forward);
    	m_move.addObject(backward, backward);
    	m_move.addObject(turnLeft, turnLeft);
    	m_move.addObject(turnRight, turnRight);
    	return (String) m_move.getSelected();
    }
    
	/**
	 * moveCommand() -- Determines what command to run
	 * 
	 * @return command
	 */
    public Command moveCommand()
    {
    	String move = getMoveAction();
    	Command command = new NoMove();
    	
    	switch(move)
    	{
    	case noMove:
    		command = new NoMove();
    		break;
    	case forward:
    		command = new DriveToDistanceWithSingleEncoder(42, 0, 0.5, 0.5);
    		break;
    	case backward:
    		command = new DriveToDistanceWithSingleEncoder(-42, 0, 0.5, 0.5);
    		break;
    	case turnLeft:
    		break; // NoMove()
    	case turnRight:
    		break; // NoMove()
    	default:
    		break; // NoMove()
    	}
    	return command;
    }
    
    /**
     * getMoveAction() -- Determines what Move Action we want 
     *                    based on a choice on the smartDashboard
     * @return Selected Move Action
     */
    public String getMoveActionSecond()
    {
        m_move2.addDefault("Move 2", noMove);
        m_move2.addObject(forward, forward);
        m_move2.addObject(backward, backward);
        m_move2.addObject(turnLeft, turnLeft);
        m_move2.addObject(turnRight, turnRight);
        return (String) m_move2.getSelected();
    }
    
    /**
     * moveCommand() -- Determines what command to run
     * 
     * @return command
     */
    public Command moveCommandSecond()
    {
        String move = getMoveActionSecond();
        Command command = new NoMove();
        switch(move)
        {
        case noMove:
            command = new NoMove();
            break;
        case forward:
            command = new DriveToDistanceWithSingleEncoder(42, 0, 0.5, 0.5);
            break;
        case backward:
            command = new DriveToDistanceWithSingleEncoder(-42, 0, 0.5, 0.5);
            break;
        case turnLeft:
            break; // NoMove()
        case turnRight:
            break; // NoMove()
        default:
            break; // NoMove()
        }
        return command;
    }
    
    /**
     * getMoveAction() -- Determines what Move Action we want 
     *                    based on a choice on the smartDashboard
     * @return Selected Move Action
     */
    public String getMoveActionThird()
    {
        m_move3.addDefault("Move 3", noMove);
        m_move3.addObject(forward, forward);
        m_move3.addObject(backward, backward);
        m_move3.addObject(turnLeft, turnLeft);
        m_move3.addObject(turnRight, turnRight);
        return (String) m_move3.getSelected();
    }
    
    /**
     * moveCommand() -- Determines what command to run
     * 
     * @return command
     */
    public Command moveCommandThird()
    {
        String move = getMoveActionThird();
        Command command = new NoMove();
        switch(move)
        {
        case noMove:
            command = new NoMove();
            break;
        case forward:
            command = new DriveToDistanceWithSingleEncoder(42, 0, 0.5, 0.5);
            break;
        case backward:
            command = new DriveToDistanceWithSingleEncoder(-42,  0, 0.5, 0.5);
            break;
        case turnLeft:
            break; // NoMove()
        case turnRight:
            break; // NoMove()
        default:
            break; // NoMove()
        }
        return command;
    }
}
