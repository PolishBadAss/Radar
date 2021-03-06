package com.example.aplikacja_testowa1;

import java.util.List;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;

public class CameraBuilder {
	public static int a,b;
	/** Check if this device has a camera */
	public static boolean checkCameraHardware(Context context) {
	    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
	        // this device has a camera
	        return true;
	    } else {
	        // no camera on this device
	        return false;
	    }
	}
	
	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance(){
	    Camera c = null;
	    try {
	        c = Camera.open(); // attempt to get a Camera instance
	        c=makeCameraFaster(c);

	    }
	    catch (Exception e){
	        // Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}
	
	public static Camera makeCameraFaster(Camera camera)
	{
		
		 Parameters parameters = camera.getParameters();
		 parameters.set("jpeg-quality", 50);
		 parameters.setPictureFormat(ImageFormat.JPEG);
		 List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
		 Size size = sizes.get(Integer.valueOf((sizes.size()-1)/2)); //choose a medium resolution
		 parameters.setPictureSize((size.width/3)*2, (size.height/3)*2);
		 a=(size.width/3)*2;
		 b=(size.height/3)*2;
		//set color efects to none
		 parameters.setColorEffect(Camera.Parameters.EFFECT_NONE);
		  //set antibanding to none
		 if (parameters.getAntibanding() != null) {
		 parameters.setAntibanding(Camera.Parameters.ANTIBANDING_OFF);
		 }

		 // set white ballance
		 if (parameters.getWhiteBalance() != null) {
		 parameters.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_CLOUDY_DAYLIGHT);
		 }

		  //set flash
		 if (parameters.getFlashMode() != null) {
		 parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
		 }

		  //set zoom
		 if (parameters.isZoomSupported()) {
		 parameters.setZoom(0);
		 }

		 //set focus mode
		 parameters.setExposureCompensation(0);
		 parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
		 camera.setParameters(parameters);
		 return camera;
	}
	
	
	
	

}
