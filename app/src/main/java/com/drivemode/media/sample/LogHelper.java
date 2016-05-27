package com.drivemode.media.sample;

import android.util.Log;

/**
 * @author KeishinYokomaku
 */
public final class LogHelper {
	private static final String TAG = LogHelper.class.getSimpleName();

	private LogHelper() {
		throw new AssertionError();
	}

	public static void logError(Throwable throwable) {
		Log.e(TAG, "observed error: ", throwable);
	}
}
