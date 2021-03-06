package com.example.aplikacja_testowa1;

import java.io.File;
import java.util.concurrent.DelayQueue;

import com.example.cameraUse.TakePhoto;
import com.example.helpers.StorageHelper;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class CameraActivity extends Activity {

    private Camera mCamera;
    private CameraPreview mPreview;
    public static boolean singlePhotoTaken=false;
    public static int buttonPushedCounter=0;
    public static File folder;

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
     // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

		boolean sCheck=StorageHelper.sHepler();
		String sc=String.valueOf(sCheck);
		Log.d("IRLOG",sc);
		// Check media
		
		
	    folder = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "iRost");
	    
        boolean success = false;
        if(!folder.exists()){
            success = folder.mkdirs();
        }
        if (!success){ 
            Log.d("IRLOG","Folder not created.");
        }
        else{
        	Log.d("IRLOG","Folder created!");
        }
	    
        
    
    
 // Add a listener to the Capture button
    final Button captureButton = (Button) findViewById(R.id.Record);
    captureButton.setOnClickListener(
        new View.OnClickListener() 
        {
            @Override
            public void onClick(View v) 
            {
            	Log.d("IRLOG","Clicked!");
            	captureButton.setText("S\nT\nO\nP");
            	buttonPushedCounter++;
            	mCamera.takePicture(null, null, TakePhoto.mPicture);
            }
        }
    );}
}