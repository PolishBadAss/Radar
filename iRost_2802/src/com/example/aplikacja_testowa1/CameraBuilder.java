package com.example.aplikacja_testowa1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.IntentSender.SendIntentException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;

public class CameraBuilder {
	
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
	    }
	    catch (Exception e){
	        // Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}
	
	public static Camera makeCameraFaster(Camera camera)
	{
		
		 Parameters parameters = camera.getParameters();
		 parameters.set("jpeg-quality", 70);
		 parameters.setPictureFormat(ImageFormat.JPEG);
		 List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
		 Size size = sizes.get(Integer.valueOf((sizes.size()-1)/2)); //choose a medium resolution
		 parameters.setPictureSize(size.width, size.height);
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
		 parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
		 camera.setParameters(parameters);
		 return camera;
	}
	
	
	
	

}
