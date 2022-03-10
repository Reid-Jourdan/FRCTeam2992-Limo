// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Robot.

package frc.robot;

import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in
 * the project.
 */
public class Robot extends TimedRobot {

    private Command autoCommand;

    public static RobotContainer mRobotContainer;

    private int vibrateCounter = 0;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer. This will perform all our button bindings,
        // and put our
        // autonomous chooser on the dashboard.
        mRobotContainer = RobotContainer.getInstance();
        HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_RobotBuilder);
        mRobotContainer.mDrivetrain.navx.zeroYaw();
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and
     * test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler. This is responsible for polling buttons, adding
        // newly-scheduled
        // commands, running already-scheduled commands, removing finished or
        // interrupted commands,
        // and running subsystem periodic() methods. This must be called from the
        // robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit() {
        mRobotContainer.mDrivetrain.setDriveNeutralMode(NeutralMode.Brake);
        mRobotContainer.mDrivetrain.setTurnNeutralMode(NeutralMode.Brake);
        stopVibrate();
    }

    @Override
    public void disabledPeriodic() {
    }

    /**
     * This autonomous runs the autonomous command selected by your
     * {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit() {
        // m_autonomousCommand = mRobotContainer.getAutonomousCommand();

        // // schedule the autonomous command (example)

        // Set the Drive Train to Brake
        mRobotContainer.mDrivetrain.setDriveNeutralMode(NeutralMode.Brake);
        mRobotContainer.mDrivetrain.setTurnNeutralMode(NeutralMode.Brake);

        // Set the Drive Motors Current Limit
        mRobotContainer.mDrivetrain.setDriveCurrentLimit(60.0, 60.0);

        // Set the Drive Motors Ramp Rate
        mRobotContainer.mDrivetrain.setDriveRampRate(0.0);

        mRobotContainer.mDrivetrain.resetOdometry();
        mRobotContainer.mDrivetrain.navx.zeroYaw();

        // Get the Autonomous Command
        autoCommand = mRobotContainer.getAutoCommand();

        // Run the Auto Command
        if (autoCommand != null) {
            autoCommand.schedule();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.

        if (autoCommand != null) {
            autoCommand.cancel();
        }

        mRobotContainer.mDrivetrain.setDriveNeutralMode(NeutralMode.Brake);
        mRobotContainer.mDrivetrain.setTurnNeutralMode(NeutralMode.Brake);

        mRobotContainer.mDrivetrain.setDriveCurrentLimit(40.0, 40.0);
        mRobotContainer.mDrivetrain.setDriveRampRate(0.25);

        mRobotContainer.mDrivetrain.resetOdometry();
        resetSubsystems();
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        vibrateControllers();
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

    public void vibrateControllers() {
        if (++vibrateCounter >= 10) {

            if ((mRobotContainer.mTurret.getTurretAngleRaw() < 30)
                    || (mRobotContainer.mTurret.getTurretAngleRaw() > 330)) {
                mRobotContainer.controller0.setRumble(RumbleType.kLeftRumble, 1);
                mRobotContainer.controller0.setRumble(RumbleType.kRightRumble, 1);
                mRobotContainer.controller1.setRumble(RumbleType.kLeftRumble, 1);
                mRobotContainer.controller1.setRumble(RumbleType.kRightRumble, 1);
            } else {
                mRobotContainer.controller0.setRumble(RumbleType.kLeftRumble, 0.0);
                mRobotContainer.controller0.setRumble(RumbleType.kRightRumble, 0.0);
                mRobotContainer.controller1.setRumble(RumbleType.kLeftRumble, 0.0);
                mRobotContainer.controller1.setRumble(RumbleType.kRightRumble, 0.0);
            }
            vibrateCounter = 0;
        }

    }

    public void stopVibrate() {

            mRobotContainer.controller0.setRumble(RumbleType.kLeftRumble, 0.0);
            mRobotContainer.controller0.setRumble(RumbleType.kRightRumble, 0.0);
            mRobotContainer.controller1.setRumble(RumbleType.kLeftRumble, 0.0);
            mRobotContainer.controller1.setRumble(RumbleType.kRightRumble, 0.0);
    }

    /**
     * Reset all subsystem states to stopped
     */
    private void resetSubsystems() {
        mRobotContainer.mBottomLift.reset();
        mRobotContainer.mCargoFunnel.reset();
        mRobotContainer.mIntake.reset();
        mRobotContainer.mShooter.reset();
        mRobotContainer.mTopLift.reset();
    }
}
