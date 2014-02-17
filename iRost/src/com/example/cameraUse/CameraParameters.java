package com.example.cameraUse;

import java.util.List;

import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.graphics.ImageFormat;
import android.hardware.Camera.Parameters;

public class CameraParameters {

	
	public static Camera setParams(Camera camera)
	{
		Parameters parameters = camera.getParameters();
		 parameters.set("jpeg-quality", 70);
		 parameters.setPictureFormat(ImageFormat.JPEG);
		 List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
		 Size size = sizes.get(Integer.valueOf((sizes.size()-1)/2)); //choose a medium resolution
		 parameters.setPictureSize(size.width, size.height);
		 camera.setParameters(parameters);
		 camera.setDisplayOrientation(90);
		 return camera;
	}
}
