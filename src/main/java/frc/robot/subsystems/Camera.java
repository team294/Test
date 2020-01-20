/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;

public class Camera extends SubsystemBase {
  private UsbCamera driveCamera;

  /**
   * Creates a new Camera.
   */
  public Camera() {
    try {
      // USB drive camera
      driveCamera = CameraServer.getInstance().startAutomaticCapture();

      // Setting the JeVois resolution will select the corresponding vision module
      driveCamera.setVideoMode(VideoMode.PixelFormat.kYUYV, 640, 256, 60); 
      // driveCamera.setVideoMode(VideoMode.PixelFormat.kYUYV, 160, 120, 30); 
      // driveCamera.setVideoMode(VideoMode.PixelFormat.kMJPEG, 160, 120, 30); 
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
