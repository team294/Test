/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wheel;
import frc.robot.utilities.ColorSensor;

/**
 * Command to spin control panel an indicated number of times.
 */
public class WheelRotatePanel extends CommandBase {
  private Wheel wheel;
  private ColorSensor colorSensor;
  private String initialColor, currentColor;
  private boolean changedColor = false;
  private int halves;
  private int colorCount = 0; // counts the number of times initial color is seen by colorSensor.
                              // 1 = half rotation, 2 = full rotation.
  //private Timer timer = new Timer();
  private Timer initialColorTimer = new Timer();
  
  /**
   * @param halves number of half rotations for command to complete
   * @param wheel wheel subsystem to use
   * @param colorSensor colorSensor utility to use
   */
  public WheelRotatePanel(int halves, Wheel wheel, ColorSensor colorSensor) {
    this.wheel = wheel;
    addRequirements(wheel);
    this.colorSensor = colorSensor;
    this.halves = halves;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //timer.reset();
    initialColorTimer.reset();
    initialColor = colorSensor.getColor();
    wheel.wheelSetVoltage(3.5);
    //timer.start();
    colorCount = 0;
    changedColor = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println(colorCount);
    currentColor = colorSensor.getColor();
    if (changedColor && initialColorTimer.get() == 0 && currentColor.equals(initialColor)) initialColorTimer.start();
    else if (changedColor && initialColorTimer.get() != 0 && !currentColor.equals(initialColor)) {
      initialColorTimer.stop();
      initialColorTimer.reset();
    }

    if (!initialColor.equals(currentColor)) changedColor = true;
    else if (changedColor && initialColor.equals(currentColor) && initialColorTimer.hasPeriodPassed(0.11)) {
      colorCount++;
      changedColor = false; // must change back to false so that colorCount does not
                            // increase multiple times in one wheel slice.
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    wheel.wheelSetVoltage(0);
    initialColorTimer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (colorCount >= halves) return true;
    else return false;
  }
}
