package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.UsesHardware;
import org.firstinspires.ftc.teamcode.baseopmodes.AutonomousBaseOpMode;
import org.firstinspires.ftc.teamcode.baseopmodes.HardwareMap;
import org.firstinspires.ftc.teamcode.robot.RobotDriver;
import org.firstinspires.ftc.teamcode.util.Position;

/**
 * Created by gescalona on 2/8/19.
 */

public class AutoHelper implements UsesHardware {
    //<editor-fold desc="Setting up">
    private AutonomousBaseOpMode baseOpMode;
    private RobotDriver driver;
    private HardwareMap map;
    private Telemetry telemetry;
    private final double SPEED = 0.6d;
    private final double pullDownHeight = 5.1;
    public AutoHelper(AutonomousBaseOpMode baseOpMode) {
        this.baseOpMode = baseOpMode;
        this.driver = RobotDriver.getDriver();
        this.map = baseOpMode.getMap();
    }
    //</editor-fold desc="DcMotorSetup">
    public void land(){
        driver.extendPullDownBar(pullDownHeight - 0.4, 0.77d);
        driver.gyroTurn(-15, 0.2, 2.5);

        driver.mecanumDriveForward(3, SPEED);
        driver.gyroTurn(15, 0.2, 2.7);
        //driver.mecanumDriveRight(2, SPEED);
        driver.mecanumDriveForward(12, SPEED);
    }

    public void knock(Position position){
        switch(position){
            case LEFT:
                driver.mecanumDriveLeft(20, SPEED);
                driver.gyroTurn(driver.getAngle(), 0.2 , 3.5);
                break;
            case RIGHT:
                driver.mecanumDriveRight(20, SPEED);
                driver.gyroTurn(driver.getAngle(), 0.2 , 3.5);
                break;
            case CENTER:
                driver.mecanumDriveRight(5, 1);
                break;
            case NULL:
                break;
        }
        driver.mecanumDriveForward(10, SPEED);
    }


    @Override
    public void hardwareInit(){ //don't do this

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
}
