package asynchronous;

import com.example.aplikacja_testowa1.CameraActivity;
import com.example.cameraUse.TakePhoto;

import android.os.AsyncTask;

public class TakePictureTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPostExecute(Void result) {
        // This returns the preview back to the live camera feed
    	
    }

    @Override
    protected Void doInBackground(Void... params) {
    	CameraActivity.mCamera.startPreview();
        //CameraActivity.mCamera.takePicture(null, null, TakePhoto.mPicture);
        return null;
    }

}