package com.example.cameraUse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.util.Log;

import com.example.aplikacja_testowa1.CameraActivity;


public class FastBurst {

	public static byte[][] byteArr = new byte[100][];
	public static int counter=0;
	protected static final int MEDIA_TYPE_IMAGE = 1;
	protected static FileOutputStream fos;
	
	
	
	public static void saveThemAll(byte[][] ba,int counter)
	{
		for(int i=0;i<counter;i++)
		{
			final File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE,i);
			if (pictureFile == null)
			{	
				Log.d("IRLOG", "Error creating media file, check storage permissions: ");
				return;
			}
			try 
			{
				fos = new FileOutputStream(pictureFile);
				byte[] data=ba[i];
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
		
	}
	
	public static  File getOutputMediaFile(int type,int c)
	{
	    // Create a media file name
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(CameraActivity.folder.getPath() + File.separator +
	        "IMG_"+ c + ".jpg");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}
	
}
