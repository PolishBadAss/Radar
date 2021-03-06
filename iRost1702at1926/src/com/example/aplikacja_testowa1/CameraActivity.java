package com.example.aplikacja_testowa1;

import com.example.cameraUse.TakePhoto;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class CameraActivity extends Activity {

    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of Camera
        boolean ifCam = com.example.aplikacja_testowa1.CameraBuilder.checkCameraHardware(getBaseContext());
        mCamera = com.example.aplikacja_testowa1.CameraBuilder.getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        
        
    
    
 // Add a listener to the Capture button
    Button captureButton = (Button) findViewById(R.id.Record);
    captureButton.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get an image from the camera
                mCamera.takePicture(null, null, TakePhoto.mPicture);
            }
        }
    );}
}