/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.opmodes.driver;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.baseopmodes.DriverBaseOpMode;
import org.firstinspires.ftc.teamcode.robot.RobotDriver;

/**
 * Ultro
 * DriverOpModeDriver.java
 * Purpose: Extends from DriverBaseOpMode
 *
 * @version 1.0 10/11/2018
 */
@TeleOp(name="Driver Op Mode EXPERIMENTAL", group="driver")
public class DriverOpModeDriverWithExperimentalControls extends DriverBaseOpMode {
    // Declare OpMode members.
    private boolean lastgyro_spin = false;
    double pos = 0;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void initb(){

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        this.runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry

        // Choose to drive using either Tank Mode, or POV Mode
        // Comment out the method that's not used.  The default below is POV.

        // POV Mode uses left stick to go forward, and right stick to turn.
        // - This uses basic math to combine motions and is easier to drive straight.
        double leftStickY = gamepad1.left_stick_y;
        double leftStickX = gamepad1.left_stick_x;

        double rightStickX = gamepad1.right_stick_x;

        RobotDriver.getDriver().mecanumDrive(leftStickY, leftStickX, rightStickX);
        if(gamepad1.right_trigger > 0){
            //getLift().setPower(gamepad1.left_trigger);
        }else {
            //getLift().setPower(-gamepad1.left_trigger/2);
        }
        if(gamepad1.y || gamepad2.y){
            pos = 0;
        }else if(gamepad1.a || gamepad2.a){
            pos = 1;
        }else if(gamepad1.x || gamepad1.b || gamepad2.x || gamepad2.b){
            pos = 0.6;
        }
        this.getBucket().setPosition(pos);
        if(gamepad1.right_trigger > 0){
            getRightpuldaun().setPower(gamepad1.right_trigger);
            getLeftpuldaun().setPower(gamepad1.right_trigger);
        }else {
            getRightpuldaun().setPower(-gamepad1.left_trigger);
            getLeftpuldaun().setPower(-gamepad1.left_trigger);
        }

        if (gamepad1.dpad_left || !lastgyro_spin) {
            lastgyro_spin = true;
            new Thread(() -> {
                RobotDriver.getDriver().gyroTurn(-90, 0.7);
                telemetry.addLine("Spinning left");
                lastgyro_spin = false;
            }).start();
        }
        if (gamepad1.dpad_right || !lastgyro_spin) {
            lastgyro_spin = true;
            new Thread(() -> {
                RobotDriver.getDriver().gyroTurn(90, 0.7);
                telemetry.addLine("Spinning Right");
                lastgyro_spin = false;
            }).start();
        }
        if (gamepad1.dpad_down || !lastgyro_spin) {
            lastgyro_spin = true;
            new Thread(() -> {
                RobotDriver.getDriver().gyroTurn(180, 1);
                telemetry.addLine("Spinning 180");
                lastgyro_spin = false;
            }).start();
        }
        if (gamepad1.dpad_up || !lastgyro_spin) {
            lastgyro_spin = true;
            new Thread(() -> {
                RobotDriver.getDriver().gyroTurn(10, 1);
                telemetry.addLine("Spinning 10 degrees to the right");
                lastgyro_spin = false;
            }).start();
        }
        telemetry.addData("IsSpinning?", lastgyro_spin);
        telemetry.update();
        // Tank Mode uses one stick to control each wheel.
        // - This requires no math, but it is hard to drive forward slowly and keep straight.
        // leftPower  = -gamepad1.left_stick_y ;
        // rightPower = -gamepad1.right_stick_y ;

        // Send calculated power to wheels


        // Show the elapsed game time and wheel power.
        /*
        telemetry.addData("A button", gamepad1.a);
        telemetry.addData("Angle", RobotDriver.getDriver().getAngle());
        telemetry.addData("CurrentAngle", RobotDriver.getDriver().getCurrentAngle());
        telemetry.addData("RelativeAngle", RobotDriver.getDriver().getRelativeAngle());
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)");
        telemetry.update();
        */
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stopb() {

    }

}
