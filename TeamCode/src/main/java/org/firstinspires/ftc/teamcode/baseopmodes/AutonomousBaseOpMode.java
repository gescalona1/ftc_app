package org.firstinspires.ftc.teamcode.baseopmodes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.UsesHardware;
import org.firstinspires.ftc.teamcode.robot.RobotDriver;

/**
 * Ultro
 * AutonomousBaseOpMode.java
 * Purpose: Base OP mode for all autonomous classes
 *
 * @version 1.0 10/11/2018
 */
public abstract class AutonomousBaseOpMode extends LinearOpMode implements UsesHardware {
    protected RobotDriver driver = RobotDriver.getDriver();
    protected HardwareMap map;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Hardware Initialization:", "Not done");
        telemetry.update();

        hardwareInit();
        RobotDriver.getDriver().setHardwareMap(this);
        RobotDriver.getDriver().resetAngle();
        telemetry.addData("Hardware Initialization:", "Finished");
        telemetry.addData("Gyro init", getImu().isGyroCalibrated());
        telemetry.update();
        prerun();
        waitForStart();
        resetStartTime();
        run();
        RobotDriver.getDriver().setHardwareMap(null);
        if(isStopRequested()){
            if (getTfod() != null) {
                getTfod().shutdown();
            }
        }
    }
    protected abstract void prerun();
    protected abstract void run();

    @Override
    public void hardwareInit(){
        map = new HardwareMap(hardwareMap);
        map.hardwareInit(telemetry);
    }
    @Override
    public boolean playRick(){
        return map.playRick();
    }
    @Override
    public boolean playUSSR(){
        return map.playUSSR();
    }
    @Override
    public final DcMotor getLeftFrontDrive() {
        return map.getLeftFrontDrive();
    }

    @Override
    public final DcMotor getRightFrontDrive() {
        return map.getRightFrontDrive();
    }

    @Override
    public final DcMotor getLeftBackDrive() {
        return map.getLeftBackDrive();
    }

    @Override
    public final DcMotor getRightBackDrive() {
        return map.getRightBackDrive();
    }


    @Override
    public DcMotor getRightpuldaun() {
        return map.getRightpuldaun();
    }

    @Override
    public DcMotor getLeftpuldaun() {
        return map.getLeftpuldaun();
    }

    @Override
    public Servo getIntake() {
        return map.getIntake();
    }

    @Override
    public DcMotor getExtend() { return map.getExtend(); }

    @Override
    public DcMotor getRotate() {
        return map.getRotate();
    }


    @Override
    public final Servo getBucket() {
        return map.getBucket();
    }

    @Override
    public final Servo getMarker() {
        return map.getMarker();
    }
    @Override
    public final BNO055IMU getImu() {
        return map.getImu();
    }

    @Override
    public final int getCameraViewId(){
        return map.getCameraViewId();
    }

    @Override
    public final VuforiaLocalizer getVuforia() {
        return map.getPhoneVuforia();
    }

    @Override
    public final TFObjectDetector getTfod() {
        return map.getTfod();
    }

    @Override
    public VuforiaTrackable getRelicTemplate() {
        return map.getRelicTemplate();
    }

    public final String getTfodModelAsset() {
        return map.getTfodModelAsset();
    }
    public final String getLabelGoldMineral() {
        return map.getLabelGoldMineral();
    }
    public final String getLabelSilverMineral() {
        return map.getLabelSilverMineral();
    }

    public Rev2mDistanceSensor getFrontDSensor() {
        return map.getFrontDSensor();
    }

    public Rev2mDistanceSensor getLeftDSensor() {
        return map.getLeftDSensor();
    }

    public Rev2mDistanceSensor getRightDSensor() {
        return map.getRightDSensor();
    }

    public HardwareMap getMap() {
        return map;
    }
}
