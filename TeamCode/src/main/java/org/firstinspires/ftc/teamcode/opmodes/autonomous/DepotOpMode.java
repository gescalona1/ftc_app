/* Copyright (c) 2018 FIRST. All rights reserved.
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

package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.baseopmodes.AutonomousBaseOpMode;
import org.firstinspires.ftc.teamcode.util.FindMineralRunnable;
import org.firstinspires.ftc.teamcode.util.Position;

/**
 * This 2018-2019 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the gold and silver minerals.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@Autonomous(name = "Depot Sample", group = "auto")
//@Disabled
public class DepotOpMode extends AutonomousBaseOpMode {
    private Position position = null;
    private final int CHECKS = 5;
    private final double SPEED = 0.80;
    private final double pullDownHeight = 5.1;
    private AutoHelper autoHelper = new AutoHelper(this);
    @Override
    protected void prerun() {
        FindMineralRunnable.setRecordData(true);
        resetStartTime();
    }

    @Override
    protected void run() {
        int i = 0;
        position = FindMineralRunnable.getCurrentPosition();
        while(true){
            if(isStopRequested()) break;
            if(i >= CHECKS) {
                FindMineralRunnable.forceStop();
                telemetry.addLine(String.format("Passed accuracy check: Position %s", position));
                break;
            }
            if(position == FindMineralRunnable.getCurrentPosition()) i++;
            else {
                run();
                return; // make sure it only runs this once
            }
            if(getRuntime() > 5){ //if 5 seconds elapsed without it being accurate, just set it
                position = FindMineralRunnable.getCurrentPosition();
                telemetry.addLine("Runtime too long, going over");
                break;
            }
        }
        autoHelper.land();

        position = (position != null) ? position : Position.RIGHT;
        FindMineralRunnable.setRecordData(false);
        FindMineralRunnable.forceStop();

        autoHelper.knock(position);
        driver.mecanumDriveBackward(4, SPEED);
        driver.mecanumDriveLeft(SPEED);
        while(!(getFrontDSensor().getDistance(DistanceUnit.INCH) <= 5.0) && opModeIsActive()){
            if(isStopRequested()) break;
            driver.mecanumDriveForward(0);
        }
        driver.gyroTurn(45, 0.55,4.5);
        driver.mecanumDriveLeft(6, SPEED);
        driver.mecanumDriveRight(1, SPEED);
        driver.mecanumDriveForward(1); //distance
        while(!(getFrontDSensor().getDistance(DistanceUnit.INCH) <= 20.0) && opModeIsActive()){
            if(isStopRequested()) break;
            driver.mecanumDriveForward(0);
        }
        /*
        sleep(6000);
        driver.mecanumDriveForward(0);
        driver.mecanumDriveBackward(3, SPEED);
        */
        getMarker().setPosition(1);
        sleep(2000);
        driver.mecanumDriveBackward(18, SPEED);
    }
}
