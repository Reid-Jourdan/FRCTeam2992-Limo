// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TopLift extends SubsystemBase {
  /** Creates a new TopLift. */
  private WPI_VictorSPX topLiftMotor;

  public TopLift() {
    topLiftMotor = new WPI_VictorSPX(24);
    topLiftMotor.setInverted(false);
    topLiftMotor.setNeutralMode(NeutralMode.Coast);    
    topLiftMotor.setStatusFramePeriod(1, 255);
    topLiftMotor.setStatusFramePeriod(2, 255);
    topLiftMotor.setStatusFramePeriod(3, 255);
    topLiftMotor.setStatusFramePeriod(4, 255);
    topLiftMotor.setStatusFramePeriod(8, 255);
    topLiftMotor.setStatusFramePeriod(10, 255);
    topLiftMotor.setStatusFramePeriod(12, 255);
    topLiftMotor.setStatusFramePeriod(13, 255);
    topLiftMotor.setStatusFramePeriod(14, 255);
    topLiftMotor.setStatusFramePeriod(21, 255);

    addChild("topLiftMotor", topLiftMotor);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setTopLiftSpeed(double speed) {
    topLiftMotor.set(ControlMode.PercentOutput, speed);
  }

  
}
