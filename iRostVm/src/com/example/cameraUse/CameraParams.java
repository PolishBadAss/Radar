package com.example.cameraUse;

import java.io.IOException;
import java.util.List;

import com.example.aplikacja_testowa1.CameraBuilder;

import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.view.SurfaceHolder;

public class CameraParams 
{	
	public static SurfaceHolder setParams(Camera camera,SurfaceHolder holder)
	{
	
		 try {
	            camera = Camera.open();
	            camera.setDisplayOrientation(90);
	            camera.setPreviewDisplay(holder);
	            Camera.Parameters parameters = camera.getParameters();
	            List<Size> sizes = parameters.getSupportedPictureSizes();
	            parameters.setPictureSize(sizes.get(0).width, sizes.get(0).height); 
	            parameters.set("orientation","portrait");
	            //parameters.setPreviewSize(viewWidth, viewHeight);
	            List<Size> size = parameters.getSupportedPreviewSizes();
	            parameters.setPreviewSize(size.get(0).width, size.get(0).height);
	            camera.setParameters(parameters);
	            //Fast
	            camera=CameraBuilder.makeCameraFaster(camera);
	            //Fast
	            camera.startPreview();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		return holder;
	}
	
	 public void surfaceCreated(SurfaceHolder holder) {
	        // The Surface has been created, now tell the camera where to draw the preview.
	       /* try {
	        	
	            mCamera.setPreviewDisplay(holder);
	            
	            mCamera.startPreview();
	        } catch (IOException e) {
	            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
	        }*/
	    }
}
