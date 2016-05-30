package com.drivemode.media.audio;

import android.database.Cursor;
import android.provider.MediaStore;

import com.drivemode.media.common.BaseCursor;

/**
 * @author KeishinYokomaku
 */
@SuppressWarnings("unused") // public API
public class GenreCursor extends BaseCursor {
	public GenreCursor(Cursor cursor) {
		super(cursor);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres#_ID}
	 */
	public long id() {
		return getLong(MediaStore.Audio.Genres._ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres#NAME}
	 */
	public String displayName() {
		return getString(MediaStore.Audio.Genres.NAME);
	}
}
