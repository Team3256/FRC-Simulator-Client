package com.swerve_drive_client;

public class RobotData {
    public SwerveModule frontLeft;
    public SwerveModule frontRight;
    public SwerveModule backLeft;
    public SwerveModule backRight;

    public float rotation;
    public float xVelocity;
    public float yVelocity;

    public RobotData() {
        frontLeft = new SwerveModule();
        frontRight = new SwerveModule();
        backLeft = new SwerveModule();
        backRight = new SwerveModule();

        rotation = 0;
        xVelocity = 0;
        yVelocity = 0;
    }
}
