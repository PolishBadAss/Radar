package com.example.cameraUse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.R;
import android.R.id;
import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.provider.MediaStore.Files.FileColumns;

public class TakePhoto {

	
	 @SuppressLint("NewApi")
	public static File getOutputMediaFile(){
		 
	        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	                  Environment.DIRECTORY_PICTURES), "MyCameraApp");
	        Log.d("HERE",Environment.getExternalStorageState());
	        if (! mediaStorageDir.exists()){
	            if (! mediaStorageDir.mkdirs()){
	                Log.d("FAIL DIRECTORY", "Failed to create directory");
	                return null;
	            }
	        }
	 
	        // Create a media file name
	        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        File mediaFile;
	        Log.d("PATHOWA", mediaStorageDir.getPath());
	            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	            "IMG_"+ timeStamp + ".jpg");
	        Log.d("FULLPATHOWA",mediaStorageDir.getPath() + File.separator +
	            "IMG_"+ timeStamp + ".jpg");
	 
	        return mediaFile;
	    }
	
	public static final PictureCallback mPicture = new PictureCallback() {

	    @Override
	    public void onPictureTaken(byte[] data, Camera camera) {

	        File pictureFile = getOutputMediaFile();
	        if (pictureFile == null){
	            Log.d("WHAT>", "Error creating media file, check storage permissions: ");
	            return;
	        }

	        try {
	            FileOutputStream fos = new FileOutputStream(pictureFile);
	            fos.write(data);
	            fos.close();
	        } catch (FileNotFoundException e) {
	            Log.d("WAT?", "File not found: " + e.getMessage());
	        } catch (IOException e) {
	            Log.d("WOPR?", "Error accessing file: " + e.getMessage());
	        }
	    }
	};
	
	

}