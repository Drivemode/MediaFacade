package com.drivemode.media.video;

import android.database.Cursor;
import android.provider.MediaStore;

import com.drivemode.media.common.BaseCursor;

/**
 * @author KeishinYokomaku
 */
public class VideoCursor extends BaseCursor {
	public static final String TAG = VideoCursor.class.getSimpleName();

	public VideoCursor(Cursor cursor) {
		super(cursor);
	}

	/**
	 * @return Data for {@link MediaStore.Images.Media#_ID}
	 */
	public long id() {
		return getLong(MediaStore.Video.Media._ID);
	}
}
