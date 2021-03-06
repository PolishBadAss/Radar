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
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.provider.MediaStore.Files.FileColumns;

public class TakePhoto {

	
	protected static final int MEDIA_TYPE_IMAGE = 1;

	public static  File getOutputMediaFile(int type)
	{
	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(CameraActivity.folder.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}
	
	public static final PictureCallback mPicture = new PictureCallback() {

	    @Override
	    public void onPictureTaken(final byte[] data, final Camera camera) 
	    {
        	camera.startPreview();
	        final File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
	        if (pictureFile == null)
	        {
	            Log.d("IRLOG", "Error creating media file, check storage permissions: ");
	            return;
	        }
	        	new Thread(new Runnable() 
	        	{
	                public void run() 
	                {
	                   	FileOutputStream fos;
						try 
						{
							fos = new FileOutputStream(pictureFile);
							fos.write(data);
			    	        fos.close();
						} 
						catch (FileNotFoundException e) 
						{
							e.printStackTrace();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
	    	           
	                }
	            }).start();
	            
	        	
	            // Taking pictures loop!!
	            if(CameraActivity.buttonPushedCounter==1)
	            {
	                takeAnother(camera,mPicture);	                           	
	            }
	            // Taking pictures loop!
	       
	       
	    }
	};
	
	public static void takeAnother(Camera camera,PictureCallback mPicture)
	{
		camera.takePicture(null, null, null, TakePhoto.mPicture);
	}

}
