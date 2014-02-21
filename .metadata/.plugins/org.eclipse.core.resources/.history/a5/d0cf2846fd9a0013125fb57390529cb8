package com.example.cameraUse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.aplikacja_testowa1.CameraActivity;
import com.example.helpers.StorageHelper;

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

	
	protected static final int MEDIA_TYPE_IMAGE = 1;

	public static  File getOutputMediaFile(int type){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

		boolean sCheck=StorageHelper.sHepler();
		String sc=String.valueOf(sCheck);
		Log.d("IRLOG",sc);
		// Check media
		
		
	    File folder = new File(Environment.getExternalStoragePublicDirectory(
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
	    

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(folder.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}
	
	public static final PictureCallback mPicture = new PictureCallback() {

	    @Override
	    public void onPictureTaken(byte[] data, Camera camera) {
	    	camera.startPreview();
	        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
	        if (pictureFile == null){
	            Log.d("IRLOG", "Error creating media file, check storage permissions: ");
	            return;
	        }

	        try {
	            FileOutputStream fos = new FileOutputStream(pictureFile);
	            fos.write(data);
	            fos.close();
	            Log.d("IRLOG","SinglePhotoTaken Variable set to true");
	            CameraActivity.singlePhotoTaken=true;
	        } catch (FileNotFoundException e) {
	            Log.d("IRLOG", "File not found: " + e.getMessage());
	        } catch (IOException e) {
	            Log.d("IRLOG", "Error accessing file: " + e.getMessage());
	        }
	    }
	};
	
	public static void takeThem(Camera camera,PictureCallback mPicture)
	{
		camera.takePicture(null, null, TakePhoto.mPicture);
	}

}
