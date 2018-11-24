package org.usfirst.frc.team302.robot;

import org.usfirst.frc.team302.robot.commands.GoToClimbEndPosition;
import org.usfirst.frc.team302.robot.commands.GoToClimbHookPosition;
import org.usfirst.frc.team302.robot.commands.GoToDrawbridgeEndPosition;
import org.usfirst.frc.team302.robot.commands.GoToDrawbridgeStartPosition;
import org.usfirst.frc.team302.robot.commands.GoToExtendedToPrepareToClimbPosition;
import org.usfirst.frc.team302.robot.commands.GoToPortcullisEndPosition;
import org.usfirst.frc.team302.robot.commands.GoToPortcullisStartPosition;
import org.usfirst.frc.team302.robot.commands.GoToStartingPosition;
import org.usfirst.frc.team302.robot.commands.GoToUnderLowBarPosition;
import org.usfirst.frc.team302.robot.utilities.MathCompares;

import edu.wpi.first.wpilibj.Joystick;
/**
 * BlackBox -- This class makes the Black Box work
 * 
 * version1 3/30/2016 -- Josh Baker: made the class
 */
public class BlackBox
{
    private Joystick m_blackBox;
    public static boolean button1;
    public static boolean button2;
    public static boolean button3;
    public static boolean button4;
    public static boolean button5;
    public static boolean button6;
    public static boolean button7;
    public static boolean button8;
    public static boolean button9;
    public static boolean button10;
    public static boolean button11;
    public static boolean button12;
    
    /**
     * CREATOR
     */
    public BlackBox()
    {
        m_blackBox = new Joystick(1);
        button1 = false;
        button2 = false;
        button3 = false;
        button4 = false;
        button5 = false;
        button6 = false;
        button7 = false;
        button8 = false;
        button9 = false;
        button10 = false;
        button11 = false;
        button12 = false;
    }
    
    /**
     * getAxis1() -- The raw axis value of the first six buttons
     * 
     * @return m_blackBox.getRawAxis(0);
     */
    public double getAxis1()
    {
        return m_blackBox.getRawAxis(0);
    }
    
    /**
     * getAxis2() -- The raw axis value of the next six buttons
     * 
     * @return m_blackBox.getRawAxis(1);
     */
    public double getAxis2()
    {
        return m_blackBox.getRawAxis(1);
    }
    
    /**
     * getAxis3() -- The raw axis value of the dial
     * 
     * @return m_blackBox.getRawAxis(2);
     */
    public double getAxis3()
    {
        return m_blackBox.getRawAxis(2);
    }
    
    /**
     * getAxis4() -- The raw axis value of the shoulder wiggle stick
     * 
     * @return m_blackBox.getRawAxis(3);
     */
    public double getAxis4()
    {
        return m_blackBox.getRawAxis(3);
    }
    
    /**
     * getAxis5() -- The raw axis value of the elbow wiggle stick
     * 
     * @return m_blackBox.getRawAxis(4);
     */
    public double getAxis5()
    {
        return m_blackBox.getRawAxis(4);
    }
    
    /**
     * isButtonPressed() -- Sets buttons to true when they are pressed.
     *                      Only one works at a time
     */
    public void isButtonPressed()
    {
        double axis1 = getAxis1();
        double axis2 = getAxis2();
        
        if(MathCompares.isEqual(axis1, 0.118, 0.008))
        {
            button1 = true;
        }
        else if(MathCompares.isEqual(axis1, 0.102, 0.001))
        {
            button2 = true;
        }
        else if(MathCompares.isEqual(axis1, 0.079, 0.001))
        {
            button3 = true;
        }
        else if(MathCompares.isEqual(axis1, 0.055, 0.001))
        {
            button4 = true;
        }
        else if(MathCompares.isEqual(axis1, 0.031, 0.001))
        {
            button5 = true;
        }
        else if(MathCompares.isEqual(axis1, 0.016, 0.001))
        {
            button6 = true;
        }
        else if(MathCompares.isEqual(axis2, 0.118, 0.001))
        {
            button7 = true;
        }
        else if(MathCompares.isEqual(axis2, 0.102, 0.001))
        {
            button8 = true;
        }
        else if(MathCompares.isEqual(axis2, 0.079, 0.001))
        {
            button9 = true;
        }
        else if(MathCompares.isEqual(axis2, 0.055, 0.001))
        {
            button10 = true;
        }
        else if(MathCompares.isEqual(axis2, 0.031, 0.001))
        {
            button11 = true;
        }
        else if(MathCompares.isEqual(axis2, 0.016, 0.001))
        {
            button12 = true;
        }
        else
        {
            button1 = false;
            button2 = false;
            button3 = false;
            button4 = false;
            button5 = false;
            button6 = false;
            button7 = false;
            button8 = false;
            button9 = false;
            button10 = false;
            button11 = false;
            button12 = false;
        }
    }
    
    /**
     * activateCommand() -- when a button is pressed, activate the command associated with it
     */
    public void activateCommand()
    {
        isButtonPressed();
        if(button1)
        {
            new GoToUnderLowBarPosition().start();
        }
        if(button2)
        {
            new GoToExtendedToPrepareToClimbPosition().start();
        }
        if(button3)
        {
            new GoToPortcullisStartPosition().start();
        }
        if(button4)//TODO:convert to labview
        {
            new GoToUnderLowBarPosition().start();
        }
        if(button5)
        {
            new GoToDrawbridgeStartPosition().start();
        }
        if(button6)
        {
            
        }
        if(button7)
        {
            new GoToStartingPosition().start();
        }
        if(button8)
        {
            new GoToClimbEndPosition().start();
        }
        if(button9)
        {
            new GoToClimbHookPosition().start();
        }
        if(button10)
        {
            new GoToPortcullisEndPosition().start();
        }
        if(button11)
        {
            new GoToPortcullisEndPosition().start();
        }
        if(button12)
        {
            new GoToDrawbridgeEndPosition().start();
        }
    }
    
    /**
     * shoulderStick() -- converts the range of the shoulder axis from [0.0, 0.118] to [-1, 1]
     * 
     * @return actual;
     */
    public double shoulderStick()
    {
        double shoulder = getAxis4();
        double actual = ((2 * (shoulder - 0.0))/(0.118110 - 0.0)) - 1;
        if(MathCompares.isEqual(actual, -0.067, 0.001))
        {
            return 0;
        }
        else
        {
        return -actual;
        }
    }
    
    /**
     * elbowStick() -- converts the range of the elbow axis from [0.0, 0.118] to [-1, 1]
     * 
     * @return actual;
     */
    public double elbowStick()
    {
        double elbow = getAxis5();
        double actual = ((2 * (elbow - 0.0))/(0.118110 - 0.0)) - 1;
        if(MathCompares.isEqual(actual, -0.067, 0.001))
        {
            return 0;
        }
        else
        {
        return -actual;
        }
    }
}
