package org.firstinspires.ftc.teamcode.baseopmodes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.UsesHardware;
import org.firstinspires.ftc.teamcode.robot.RobotDriver;

/**
 * Ultro
 * DriverBaseOpMode.java
 * Purpose: Base OP mode for all driver classes
 *
 * @version 1.0 10/11/2018
 */

public abstract class DriverBaseOpMode extends OpMode implements UsesHardware {
    protected ElapsedTime runtime = new ElapsedTime();
    protected org.firstinspires.ftc.teamcode.baseopmodes.HardwareMap map;

    @Override
    public void init() {
        hardwareInit();
        RobotDriver.getDriver().setHardwareMap(this);
        inita();
        initb();
    }

    @Override
    public void stop(){
        RobotDriver.getDriver().setHardwareMap(null);
        map = null;
        stopb();
    }
    private void inita(){
        this.runtime.reset();
        telemetry.addData("Status", "Initializing");
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    public abstract void initb();
    public abstract void stopb();
    @Override
    public void hardwareInit(){
        map = new org.firstinspires.ftc.teamcode.baseopmodes.HardwareMap(hardwareMap);
        map.driverHardwareInit(telemetry);
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
    public Servo getIntake() {
        return map.getIntake();
    }

    @Override
    public DcMotor getExtend() { return map.getExtend(); }

    @Override
    public DcMotor getRotate() {
        return map.getRotate();
    }
    public final DcMotor getRightpuldaun() {
        return map.getRightpuldaun();
    }
    @Override
    public final DcMotor getLeftpuldaun() {
        return map.getLeftpuldaun();
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

    public final String getTfodModelAsset() {
        return map.getTfodModelAsset();
    }
    public final String getLabelGoldMineral() {
        return map.getLabelGoldMineral();
    }
    public final String getLabelSilverMineral() {
        return map.getLabelSilverMineral();
    }

    @Override
    public VuforiaTrackable getRelicTemplate() {
        return map.getRelicTemplate();
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
}
