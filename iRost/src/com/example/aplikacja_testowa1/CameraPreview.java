package com.example.aplikacja_testowa1;

import java.io.IOException;
import java.util.List;

import com.example.cameraUse.CameraParams;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/** A basic Camera preview class */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "IRLOG";
	private SurfaceHolder mHolder;
    private Camera mCamera;
    private SurfaceView sf;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


	public void surfaceCreated(SurfaceHolder holder) {
    	 try {
	            
	            
    		 	mCamera.setDisplayOrientation(0);
	            mCamera.setPreviewDisplay(holder);
	           
	            Camera.Parameters parameters = mCamera.getParameters();
	            List<Size> sizes = parameters.getSupportedPictureSizes();
	            parameters.setPictureSize(sizes.get(0).width, sizes.get(0).height); 
	            parameters.set("orientation","portrait");
	            //parameters.setPreviewSize(viewWidth, viewHeight);
	            List<Size> size = parameters.getSupportedPreviewSizes();
	            parameters.setPreviewSize(size.get(0).width, size.get(0).height);
	            mCamera.setParameters(parameters);
	            // Testing camera parameters!!
	            // ---------------------------
            	mCamera=CameraBuilder.makeCameraFaster(mCamera);
            	// ---------------------------
            	// Testing camera parameters!!
	            mCamera.startPreview();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
    	
            if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;   }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null){
          // preview surface does not exist
          return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e){
          // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
            //Log.d("IRLOG","Start Preview Called");

        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }
}