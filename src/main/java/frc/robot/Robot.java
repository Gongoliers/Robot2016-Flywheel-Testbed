// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  
  private SpeedController left;
  private SpeedController right;
  private XboxController xbox;
  private double desiredSpeed = 0.0;

  public Robot(){
    left = new WPI_TalonSRX(4);
		right = new WPI_TalonSRX(1);
    xbox = new XboxController(1);
  }

  private void spin(double speed){
    left.set(speed);
		right.set(-speed);
  }

  @Override
  public void teleopInit() {
    desiredSpeed = 0.0;
    spin(0.0);
  }

  @Override
  public void teleopPeriodic() {

    if (xbox.getAButtonPressed()){
      desiredSpeed += 0.1;
      System.out.println(desiredSpeed);
    }

    if (xbox.getBButtonPressed()){
      desiredSpeed -= 0.1;
      System.out.println(desiredSpeed);
    }

    if (xbox.getYButtonPressed() || xbox.getXButtonPressed()){
      desiredSpeed = 0.0;
    }

    desiredSpeed = Math.min(1.0, Math.max(-1.0, desiredSpeed));

    spin(desiredSpeed);
  }
}
