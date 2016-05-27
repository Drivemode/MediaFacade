package com.drivemode.media.image;

import android.database.Cursor;
import android.provider.MediaStore;

import com.drivemode.media.common.BaseCursor;

/**
 * @author KeishinYokomaku
 */
public class ImageCursor extends BaseCursor {
	public static final String TAG = ImageCursor.class.getSimpleName();

	public ImageCursor(Cursor cursor) {
		super(cursor);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#_ID}
	 */
	public long id() {
		return getLong(MediaStore.Images.Media._ID);
	}
}
