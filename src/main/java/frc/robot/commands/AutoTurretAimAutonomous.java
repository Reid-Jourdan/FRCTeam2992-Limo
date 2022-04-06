
package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.vision.LimeLight.LedMode;

import frc.robot.subsystems.Turret;

public class AutoTurretAimAutonomous extends CommandBase {

    private Turret mTurret;
    private boolean mSetTarget;
    private double mNewTarget;

    private double turretSetAngle = 0;
    private Timer   lostTargetTime = new Timer();

    public AutoTurretAimAutonomous(Turret subsystem, boolean setTarget, double newTarget) {
        addRequirements(subsystem);

        mTurret = subsystem;
        mSetTarget = setTarget;
        mNewTarget = newTarget;
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
        mTurret.limeLightCamera.resetMedianFilters();
        mTurret.setAutoAiming(true);
        if (mSetTarget) {
            turretSetAngle = mNewTarget;
            mTurret.turretTarget = turretSetAngle;
        }
        lostTargetTime.reset();
        lostTargetTime.start();
        
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        turretSetAngle = mTurret.turretTarget;
        mTurret.limeLightCamera.setLedMode(LedMode.On);

        if (mTurret.limeLightCamera.hasTarget() && mTurret.isAutoAiming()) {
            lostTargetTime.reset();
            double xOffset = mTurret.limeLightCamera.getTargetXOffset();

            if (Math.abs(xOffset) > 0.5) {
                turretSetAngle = mTurret.getTurretAngle() + xOffset;
            } else {
                //turretSetAngle = mTurret.getTurretAngle();
            }
            mTurret.goToAngle(turretSetAngle);
        }

        else {
            if (lostTargetTime.get() > 0.25) {
                mTurret.goToAngle(turretSetAngle);
            } else {
                mTurret.setTurretSpeed(0);
            }
        }
    }

    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        mTurret.stopTurret();
        mTurret.setAutoAiming(false);
        mTurret.limeLightCamera.setLedMode(LedMode.Off);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }
}