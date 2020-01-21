/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utilities.ColorSensor;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Wheel extends SubsystemBase {
  private final WPI_TalonSRX wheelMotor = new WPI_TalonSRX(20);
  ColorSensor colorSensor;

  /**
   * Creates a new Wheel.
   */
  public Wheel(ColorSensor colorSensor) {
    wheelMotor.configFactoryDefault();
    wheelMotor.setNeutralMode(NeutralMode.Brake);
    this.colorSensor = colorSensor;
  }

  /**
   * @param voltage voltage to set wheel to
   */
  public void wheelSetVoltage(double voltage) {
    wheelMotor.setVoltage(voltage);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // ColorSensor updated values on dashboard
    SmartDashboard.putBoolean("Yellow", colorSensor.getColor() == "Yellow");
    SmartDashboard.putBoolean("Red", colorSensor.getColor()=="Red");
    SmartDashboard.putBoolean("Green", colorSensor.getColor() == "Green");
    SmartDashboard.putBoolean("Blue", colorSensor.getColor() == "Blue");
    SmartDashboard.putString("Color", colorSensor.getColor());
    SmartDashboard.putNumber("Proximity", colorSensor.getProximity());
  }
}