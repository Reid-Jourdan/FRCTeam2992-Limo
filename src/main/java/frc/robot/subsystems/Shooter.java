// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.commands.StopShooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Shooter extends SubsystemBase {
  // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

  // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

  public int mainShooterSetSpeed = Constants.defaultMainShooterSpeed;
  public int secondaryShooterSetSpeed = Constants.defaultSecondaryShooterSpeed;

  private int dashboardCounter = 0;

  // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
  private WPI_TalonSRX mainShooterLead;
  private WPI_VictorSPX mainShooterFollow;
  private WPI_TalonSRX secondaryShooterLead;
  // private WPI_VictorSPX secondaryShooterFollow;

  // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  /**
  *
  */
  public Shooter() {
    mainShooterLead = new WPI_TalonSRX(10);
    mainShooterLead.setInverted(true);
    mainShooterLead.setNeutralMode(NeutralMode.Coast);
    addChild("mainShooterLead", mainShooterLead);

    mainShooterFollow = new WPI_VictorSPX(11);
    mainShooterFollow.setInverted(false);
    mainShooterFollow.setNeutralMode(NeutralMode.Coast);
    addChild("mainShooterFollow", mainShooterFollow);
    mainShooterFollow.follow(mainShooterLead);

    secondaryShooterLead = new WPI_TalonSRX(12);
    secondaryShooterLead.setNeutralMode(NeutralMode.Coast);
    addChild("secondaryShooterLead", secondaryShooterLead);
    secondaryShooterLead.setInverted(true);

    setDefaultCommand(new StopShooter(this));
    // secondaryShooterFollow = new WPI_VictorSPX(12);

    
    // addChild("secondaryShooterFollow", secondaryShooterFollow);

    // secondaryShooterFollow.follow(secondaryShooterLead);
    // secondaryShooterFollow.setInverted(true);
    
    // secondaryShooterFollow.setNeutralMode(NeutralMode.Coast);

  }

  @Override
  public void periodic() {
    // Display the Shooter Set Speed and Current RPM
    if (++dashboardCounter >= 5) {
      SmartDashboard.putNumber("Main Shooter Set Speed", mainShooterSetSpeed);
      SmartDashboard.putNumber("Main Shooter Current RPM", getMainShooterRPM());

      SmartDashboard.putNumber("Secondary Shooter Set Speed", secondaryShooterSetSpeed);
      SmartDashboard.putNumber("Secondary Shooter Current RPM", getSecondaryShooterRPM());

      if (getMainShooterRPM() > 1000) {
        SmartDashboard.putBoolean("Shooter State", true);
      } else {
        SmartDashboard.putBoolean("Shooter State", false);
      }
      dashboardCounter = 0;
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run when in simulation

  }

  public void setMainShooterSpeed(double speed) {
    mainShooterLead.set(ControlMode.PercentOutput, speed);
  }

  public void setSecondaryShooterSpeed(double speed) {
    secondaryShooterLead.set(ControlMode.PercentOutput, speed);
  }

  public void setMainShooterVelocity(double velocity) {
    mainShooterLead.set(ControlMode.Velocity, velocity);
    mainShooterFollow.follow(mainShooterLead);
  }

  public void setSecondaryShooterVelocity(double velocity) {
    secondaryShooterLead.set(ControlMode.Velocity, velocity);
  }

  public double getMainShooterRPM() {
    return (mainShooterLead.getSelectedSensorVelocity() * 600) / (Constants.shooterEncoderPulses * 4);
  }

  public double getSecondaryShooterRPM() {
    return (secondaryShooterLead.getSelectedSensorVelocity() * 600) / (Constants.shooterEncoderPulses * 4);
  }
}
