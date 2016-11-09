package org.firstinspires.ftc.teamcode;
import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;

/**
 * Created by Marcos on 11/9/2016.
 */

public class CameraRGB{

    public Camera Camera;
    private android.graphics.Camera openFrontFacingCamera() {
        int cameraID = -1;
         Camera Cam = null;
        int numberOfCameras = android.hardware.Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
            android.hardware.Camera.getCameraInfo(i, info);
            if (info.facing == android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraID = 1;
                break;
            }
        }
        try {
            Cam = android.graphics.Camera.open(cameraID);
        } catch (Exception e) {
        }
        return Cam;
    }
}
