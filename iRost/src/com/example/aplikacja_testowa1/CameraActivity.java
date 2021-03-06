package com.example.aplikacja_testowa1;

import java.io.File;
import com.example.cameraUse.FastBurst;
import com.example.helpers.StorageHelper;

import android.app.Activity;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import asynchronous.TakePhotoUsingAsyncTask;

public class CameraActivity extends Activity {

    public static Camera mCamera;
    private CameraPreview mPreview;
    public static boolean singlePhotoTaken=false;
    public static int buttonPushedCounter=0;
    public static File folder;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of Camera
        @SuppressWarnings("unused")
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
    final AsyncTask<String, Void, String> ast=new TakePhotoUsingAsyncTask();
    captureButton.setOnClickListener(
        new View.OnClickListener() 
        {
           
			@Override
            public void onClick(View v) 
            {
				if(buttonPushedCounter==0)
            	{
					captureButton.setText("S\nT\nO\nP");
					
					ast.execute();
					//CameraActivity.buttonPushedCounter++;
	 	            //CameraActivity.mCamera.takePicture(null, null, TakePhoto.mPicture);
            	}
				else if (buttonPushedCounter==1)
				{
					ast.cancel(true);
	            	mCamera.stopPreview();
	            	FastBurst.saveThemAll(FastBurst.byteArr, FastBurst.counter);            	
				}
            	/*if(buttonPushedCounter==0)
            	{
            		captureButton.setText("S\nT\nO\nP");
            		buttonPushedCounter++;
            		// dywersja
            		//mCamera.takePicture(null, null, TakePhoto.mPicture);
            		// dywersja
    	            mCamera.takePicture(null, null, TakePhoto.mPicture);
            	}
            	else if (buttonPushedCounter==1)
            	{
            		mCamera.stopPreview();
            		FastBurst.saveThemAll(FastBurst.byteArr, FastBurst.counter);            	
            	}*/
            }
        }
    );

    }
}