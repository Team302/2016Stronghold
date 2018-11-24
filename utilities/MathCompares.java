package org.usfirst.frc.team302.robot.utilities;

//
//------------------------------------------------------------------------------------------
// Modifications
//   10-Mar-2016    Joe Witcpalek       Initial version written
//------------------------------------------------------------------------------------------
//

/**
 * MathCompares
 * 
 * This is a series of static methods that provides the ability to compare two (2) double values
 * to see how the first number compares to the second number within a zero tolerance.  The comparison
 * types available are:
 *    Less than
 *    Less than or Equal to
 *    Equal 
 *    Greater than
 *    Greater than or Equal to
 * 
 * Typically, the zeroTolerance should come from RobotMap.  So, some example comparisons would be:
 * 
 * if ( MathCompares.isLessThan( angle1, angle2, RobotMap.ANGLE_TOL_DEGREES )
 * {
 * }
 * 
 * if ( MathCompares.isLessThanOrEqual( angle1, angle2, RobotMap.ANGLE_TOL_DEGREES )
 * {
 * }
 * 
 * if ( MathCompares.isLessThan( angle1, angle2, RobotMap.ANGLE_TOL_DEGREES )
 * {
 * }
 * 
 * if ( MathCompares.isEqual( angle1, angle2, RobotMap.ANGLE_TOL_DEGREES )
 * {
 * }
 * 
 * if ( MathCompares.isGreaterThan( angle1, angle2, RobotMap.ANGLE_TOL_DEGREES )
 * {
 * }
 * 
 * if ( MathCompares.isGreaterThanOrEqual( angle1, angle2, RobotMap.ANGLE_TOL_DEGREES )
 * {
 * }
 * 
 * @author Joe Witcpalek
 * @version 1.0
 */
public class MathCompares 
{
  /**
   * isLessThan
   * 
   * Compare 2 double values,  if the first value is less than the second minus the zero tolerance
   * then it is deemed to be less than.
   * 
   * An example of how to call this would be:
   * 
   * if ( MathCompares.isLessThan( angle1, angle2, RobotMap.ANGLE_TOL_DEGREES )
   * {
   * }
   * 
   * @param valueToCheck   - value to check with the compare value
   * @param compareToValue - value to compare with 
   * @param zeroTolerance  - value where two numbers are deemed to be equal
   * @return  true if the valueToCheck is less than the compareToValue.  Otherwise false.
   */
  public static boolean isLessThan
  ( 
      double valueToCheck,        // <I> - value to check
      double compareToValue,      // <I> - value to compare with
      double zeroTolerance        // <I> - value where two numbers are deemed to be equal
  )
  {
    // subtract the zero tolerance from the target to remove the value that is equal
    // then just compare and return the answer.
    return valueToCheck < ( compareToValue - zeroTolerance ); 
  }
  
  /**
   * isLessThanOrEqual
   * 
   * Compare 2 double values,  if the first value is less than the second plus the zero tolerance
   * then it is deemed to be less than or equal.
   * 
   * An example of how to call this would be:
   * 
   * if ( MathCompares.isLessThanOrEqual( angle1, angle2, RobotMap.ANGLE_TOL_DEGREES )
   * {
   * }
   * 
   * @param valueToCheck   - value to check with the compare value
   * @param compareToValue - value to compare with 
   * @param zeroTolerance  - value where two numbers are deemed to be equal
   * @return  true if the valueToCheck is less or equal to the compareToValue.  Otherwise false.
   */
 public static boolean isLessThanOrEqual
  ( 
      double valueToCheck,        // <I> - value to check
      double compareToValue,      // <I> - value to compare with
      double zeroTolerance        // <I> - value where two numbers are deemed to be equal
  )
  {
   // add the zero tolerance to the target to get to the highest value that is deemed to be
   // equal, then just compare and return the answer.
    return valueToCheck < ( compareToValue + zeroTolerance );
  }
  
 /**
  * isEqual
  * 
  * Compare 2 double values,  if the absolute value of the first value - the second value is less
  * than the zeroTolerance, then they are equal.
  * 
  * An example of how to call this would be:
  * 
  * if ( MathCompares.isEqual( angle1, angle2, RobotMap.ANGLE_TOL_DEGREES )
  * {
  * }
  * 
  * @param valueToCheck   - value to check with the compare value
  * @param compareToValue - value to compare with 
  * @param zeroTolerance  - value where two numbers are deemed to be equal
  * @return  true if values are equal and false if they are not
  */

  public static boolean isEqual
  ( 
      double valueToCheck,        // <I> - value to check
      double compareToValue,      // <I> - value to compare with
      double zeroTolerance        // <I> - value where two numbers are deemed to be equal
  )
  {
    return Math.abs( valueToCheck - compareToValue ) < zeroTolerance;
  }
  
  /**
   * isGreaterThan
   * 
   * Compare 2 double values,  if the first value is less than the second plus the zero tolerance
   * then it is deemed to be greater than.
   * 
   * An example of how to call this would be:
   * 
   * if ( MathCompares.isGreaterThan( angle1, angle2, RobotMap.ANGLE_TOL_DEGREES )
   * {
   * }
   * 
   * @param valueToCheck   - value to check with the compare value
   * @param compareToValue - value to compare with 
   * @param zeroTolerance  - value where two numbers are deemed to be equal
   * @return  true if the valueToCheck is greater than the compareToValue.  Otherwise false.
   */
  public static boolean isGreaterThan
  ( 
      double valueToCheck,        // <I> - value to check
      double compareToValue,      // <I> - value to compare with
      double zeroTolerance        // <I> - value where two numbers are deemed to be equal
  )
  {
    // add the zero tolerance to the target to get to the highest value that is deemed to be
    // equal, then just compare and return the answer.
    return valueToCheck > ( compareToValue + zeroTolerance );
  }  
  
  /**
   * isGreaterThanOrEqual
   * 
   * Compare 2 double values,  if the first value is less than the second minus the zero tolerance
   * then it is deemed to be greater than or equal to.
   * 
   * An example of how to call this would be:
   * 
   * if ( MathCompares.isGreaterThanOrEqual( angle1, angle2, RobotMap.ANGLE_TOL_DEGREES )
   * {
   * }
   * 
   * @param valueToCheck   - value to check with the compare value
   * @param compareToValue - value to compare with 
   * @param zeroTolerance  - value where two numbers are deemed to be equal
   * @return  true if the valueToCheck is greater than or equal to the compareToValue.  Otherwise false.
   */
  public static boolean isGreaterThanOrEqual
  ( 
      double valueToCheck,        // <I> - value to check
      double compareToValue,      // <I> - value to compare with
      double zeroTolerance        // <I> - value where two numbers are deemed to be equal
  )
  {
    // subtract the zero tolerance to the target to get to the lowest value that is deemed to be
    // equal, then just compare and return the answer.
    return valueToCheck > ( compareToValue - zeroTolerance );
  }
 
}
