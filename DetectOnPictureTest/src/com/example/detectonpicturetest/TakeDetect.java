package com.example.detectonpicturetest;

import java.io.File;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import android.os.Environment;
import android.util.Log;

import com.googlecode.javacv.FrameRecorder;
import com.googlecode.javacv.OpenCVFrameRecorder;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui.CvCapture;

public class TakeDetect {

	public static Mat imgCV;
	public static ColorBlobDetector mDetector;
	public static Scalar whiteColor;
	public static Mat mSpectrum;
	public static Size SPECTRUM_SIZE = new Size(200, 64);
	
	
	public static Mat do1()
	{
		
		Scalar CONTOUR_COLOR = new Scalar(255,0,0,255);
		String src=Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures/photo.jpg";
		//File imageFile=new File(src);
		imgCV=Highgui.imread(src);
		mDetector = new ColorBlobDetector();
		whiteColor = new Scalar(0, 0, 0);
		Scalar mBlobColorRgba = converScalarHsv2Rgba(whiteColor);
		mDetector.setHsvColor(whiteColor);
		Imgproc.resize(mDetector.getSpectrum(), mSpectrum, SPECTRUM_SIZE);
		mDetector.process(imgCV);
		
		
		List<MatOfPoint> contours = mDetector.getContours();
        Imgproc.drawContours(imgCV, contours, -1, CONTOUR_COLOR);

        Mat colorLabel = imgCV.submat(4, 68, 4, 68);
        colorLabel.setTo(mBlobColorRgba);

        Mat spectrumLabel = imgCV.submat(4, 4 + mSpectrum.rows(), 70, 70 + mSpectrum.cols());
        mSpectrum.copyTo(spectrumLabel);
    

        return imgCV;
	}
	
	public static Scalar converScalarHsv2Rgba(Scalar hsvColor) {
        Mat pointMatRgba = new Mat();
        Mat pointMatHsv = new Mat(1, 1, CvType.CV_8UC3, hsvColor);
        Imgproc.cvtColor(pointMatHsv, pointMatRgba, Imgproc.COLOR_HSV2RGB_FULL, 4);

        return new Scalar(pointMatRgba.get(0, 0));
    }
	
	public static void SaveImage (Mat imgCV) {
		   Mat mIntermediateMat = new Mat();
		   Imgproc.cvtColor(imgCV, mIntermediateMat, Imgproc.COLOR_RGBA2BGR, 3);

		   File path = new File(Environment.getExternalStorageDirectory() + "/Pictures/");
		   path.mkdirs();
		   File file = new File(path, "convert.jpg");

		   String filename = file.toString();
		   Boolean bool = Highgui.imwrite(filename, mIntermediateMat);

		   if (bool)
		    Log.i("ZAPIS", "SUCCESS writing image to external storage");
		   else
		    Log.i("ZAPIS", "Fail writing image to external storage");
		}
	
	public static void tstVideo() throws Exception
	{
		String src=Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures/video.mp4";
		CvCapture capture=com.googlecode.javacv.cpp.opencv_highgui.cvCreateFileCapture(src);
		IplImage frame;
		IplImage image = null;
        IplImage prevImage = null;
        IplImage diff = null;
        
        
        FrameRecorder recorder = new OpenCVFrameRecorder(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures/wynik.mp4", 320, 240);
        recorder.setVideoCodec(com.googlecode.javacv.cpp.opencv_highgui.CV_FOURCC('M','J', 'P', 'G'));
        recorder.setFrameRate(30);
        recorder.setPixelFormat(1);
        recorder.start();
        
        
        //------------------------------------
        
        for(;;)
		{
			frame = com.googlecode.javacv.cpp.opencv_highgui.cvQueryFrame(capture);
			//instrukcja wyjscia na koniec odtwarzania
			if(frame == null) break;        
	        com.googlecode.javacv.cpp.opencv_highgui.cvShowImage("Video", frame);
			

			com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey(30);
			
			
			//jakas magia, ale dziala
			com.googlecode.javacv.cpp.opencv_imgproc.cvSmooth(frame, frame, com.googlecode.javacv.cpp.opencv_imgproc.CV_GAUSSIAN, 9, 9, 2, 2);
		            if (image == null) {
		                image = IplImage.create(frame.width(), frame.height(), com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U, 1);
		                com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor(frame, image, com.googlecode.javacv.cpp.opencv_imgproc.CV_RGB2GRAY);
		            } else {
		                prevImage = IplImage.create(frame.width(), frame.height(), com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U, 1);
		                prevImage = image;
		                image = IplImage.create(frame.width(), frame.height(), com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U, 1);
		                com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor(frame, image, com.googlecode.javacv.cpp.opencv_imgproc.CV_RGB2GRAY);
		            }

		            if (diff == null) {
		                diff = IplImage.create(frame.width(), frame.height(), com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U, 1);
		            }

		            if (prevImage != null) 
		            {

		            	com.googlecode.javacv.cpp.opencv_core.cvAbsDiff(image, prevImage, diff);
		            	com.googlecode.javacv.cpp.opencv_imgproc.cvThreshold(diff, diff, 64, 255, com.googlecode.javacv.cpp.opencv_imgproc.CV_THRESH_BINARY);
		            	recorder.record(diff);
		            }

		}
			recorder.stop();
		//wylaczenie i odblokowanie wszystkich okien

			com.googlecode.javacv.cpp.opencv_highgui.cvReleaseCapture(capture);

	}
}















