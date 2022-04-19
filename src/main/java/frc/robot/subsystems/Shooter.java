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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class Shooter extends SubsystemBase {
  // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

  // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

  private double mainShooterSetRPM = Constants.defaultMainShooterSpeed;
  private double secondaryShooterSetRPM = Constants.defaultSecondaryShooterSpeed;
  private boolean shooterCommanded = false;

  private int dashboardCounter = 0;

  // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
  private WPI_TalonFX mainShooterLead;
  private WPI_TalonFX mainShooterFollow;
  private WPI_TalonFX secondaryShooterLead;
  // private WPI_VictorSPX secondaryShooterFollow;

  // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  /**
  *
  */
  public Shooter() {
    mainShooterLead = new WPI_TalonFX(31, "CanBus2");
    mainShooterLead.setInverted(false);
    mainShooterLead.setNeutralMode(NeutralMode.Coast);
    mainShooterLead.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40.0, 60.0, 0.1));
    addChild("mainShooterLead", mainShooterLead);

    mainShooterFollow = new WPI_TalonFX(30, "CanBus2");
    mainShooterFollow.setInverted(true);
    mainShooterFollow.setNeutralMode(NeutralMode.Coast);
    mainShooterFollow.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40.0, 60.0, 0.1));
    mainShooterFollow.follow(mainShooterLead);
    addChild("mainShooterFollow", mainShooterFollow);

    secondaryShooterLead = new WPI_TalonFX(32, "CanBus2");
    secondaryShooterLead.setNeutralMode(NeutralMode.Coast);
    secondaryShooterLead.setInverted(true);
    secondaryShooterLead.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 40.0, 60.0, .25));
    addChild("secondaryShooterLead", secondaryShooterLead);
   
     }

  @Override
  public void periodic() {
    // Display the Shooter Set Speed and Current RPM
    // if (++dashboardCounter >= 5) {
      // SmartDashboard.putNumber("Main Shooter Set Speed", mainShooterSetRPM);
      // SmartDashboard.putNumber("Main Shooter Current RPM", getMainShooterRPM());

      // SmartDashboard.putNumber("Secondary Shooter Set Speed", secondaryShooterSetRPM);
      // SmartDashboard.putNumber("Secondary Shooter Current RPM", getSecondaryShooterRPM());

      // SmartDashboard.putBoolean("Main Shooter At Speed", atMainShooterRPM());
      // SmartDashboard.putBoolean("Secondary Shooter At Speed", atSecondaryShooterRPM());
      // SmartDashboard.putBoolean("Shooters at Speed", atShooterRPM());

    //   dashboardCounter = 0;
    // }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run when in simulation

  }

  public void setMainShooterPower(double speed) {
    mainShooterLead.set(ControlMode.PercentOutput, speed);
  }

  public void setSecondaryShooterPower(double speed) {
    secondaryShooterLead.set(ControlMode.PercentOutput, speed);
  }

  public void setMainShooterToTargetRPM() {
    double speed = (mainShooterSetRPM / 600.0) * (Constants.shooterEncoderPulses * 0.75);
    setMainShooterRawVelocity(speed);
  }

  private void setMainShooterRawVelocity(double velocity) {
    mainShooterLead.set(ControlMode.Velocity, velocity);
  }

  public void setSecondaryShooterToTargetRPM() {
    double speed = (secondaryShooterSetRPM / 600.0) * (Constants.shooterEncoderPulses);
    setSecondaryShooterRawVelocity(speed);
  }

  private void setSecondaryShooterRawVelocity(double velocity) {
    secondaryShooterLead.set(ControlMode.Velocity, velocity);
  }

  public double getMainShooterRPM() {
    return (mainShooterLead.getSelectedSensorVelocity() * 600) / (Constants.shooterEncoderPulses * 0.75);
  }

  public double getSecondaryShooterRPM() {
    return (secondaryShooterLead.getSelectedSensorVelocity() * 600) / (Constants.shooterEncoderPulses);
  }

  public double getMainShooterTargetRPM() {
    return mainShooterSetRPM;
  }

  public void setMainShooterTargetRPM(double mainShooterSetSpeed) {
    this.mainShooterSetRPM = mainShooterSetSpeed;
  }

  public double getSecondaryShooterTargetRPM() {
    return secondaryShooterSetRPM;
  }

  public void setSecondaryShooterTargetRPM(double secondaryShooterSetSpeed) {
    this.secondaryShooterSetRPM = secondaryShooterSetSpeed;
  }

  public boolean atMainShooterRPM() {
    return (Math.abs(getMainShooterTargetRPM() - getMainShooterRPM()) < 200.0);
  }

  public boolean atSecondaryShooterRPM() {
    return (Math.abs(getSecondaryShooterTargetRPM() - getSecondaryShooterRPM()) < 150.0);
  }

  public boolean atShooterRPM() {
    return (atMainShooterRPM() && atSecondaryShooterRPM());
  }

  public boolean isShooterCommanded() {
    return shooterCommanded;
  }

  public void setShooterCommanded(boolean shooterCommanded) {
    this.shooterCommanded = shooterCommanded;
  }

  public void reset() {
    setShooterCommanded(false);
  }
  
}
