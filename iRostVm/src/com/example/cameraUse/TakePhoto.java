package com.example.cameraUse;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;


public class TakePhoto {

	
	public static final PictureCallback mPicture = new PictureCallback() 
	{
	    @Override
	    public void onPictureTaken(final byte[] data, final Camera camera) 
	    {
        	//camera.startPreview();
        	//new TakePictureTask().execute();
        	FastBurst.byteArr[FastBurst.counter]=data;
           	FastBurst.counter++; 
	        	new Thread(new Runnable() 
	        	{
	                public void run() 
	                {
	                   	FastBurst.byteArr[FastBurst.counter]=data;
	                   	FastBurst.counter++;
	                   	//camera.takePicture(null,null,null,mPicture);
	                }
	            }).start();
	            // Taking pictures loop!!
	        	camera.takePicture(null,null,null,mPicture);
	        	// Taking pictures loop!
	       

	    }
	};
}
