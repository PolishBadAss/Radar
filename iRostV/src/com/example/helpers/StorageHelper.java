package com.example.helpers;

import android.os.Environment;
import android.util.Log;

public class StorageHelper {

	
	
	@SuppressWarnings("unused")
	public static boolean sHepler()
	{
		
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
		    Log.d("IRLOG","We can read and write the media");
		    mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		    Log.d("IRLOG","We can only read the media");
		    mExternalStorageAvailable = true;
		    mExternalStorageWriteable = false;
		} else {
		    Log.d("IRLOG","Something is wrong - storage checker!");
		    mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
	return mExternalStorageWriteable;
	}
	
}
