package asynchronous;


import com.example.aplikacja_testowa1.CameraActivity;
import com.example.cameraUse.TakePhoto;

import android.os.AsyncTask;

public class TakePhotoUsingAsyncTask extends AsyncTask<String, Void, String> 
{
		 
		 @Override
		 protected String doInBackground(String... params) 
		 {
         		CameraActivity.buttonPushedCounter++;
 	            CameraActivity.mCamera.takePicture(null, null, TakePhoto.mPicture);
 	            return null;
		 }
		 
		 @Override
		 protected void onPostExecute(String result) 
		 {
			this.cancel(true);
		 }
		 
		 @Override
		 protected void onPreExecute() 
		 {
		  // Analogicznie do metody onPostExecute, implenentujesz czynnosci do zrealizowania przed uruchomieniem w¹tku
		 }
		 
		 @Override
		 protected void onProgressUpdate(Void... values) 
		 {
		  // Natomiast metoda onProgressUpdate umozliwia aktualizwanie watku g³ównej podczas dzia³ana naszego Wymagaj¹cegoWatku
		 }
		
}