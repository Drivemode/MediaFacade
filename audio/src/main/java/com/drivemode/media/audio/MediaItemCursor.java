package com.drivemode.media.audio;

import android.database.Cursor;
import android.provider.MediaStore;

import com.drivemode.media.common.BaseCursor;

/**
 * @author KeishinYokomaku
 */
public class MediaItemCursor extends BaseCursor {
	public MediaItemCursor(Cursor cursor) {
		super(cursor);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#_ID}
	 */
	public long id() {
		return getLong(MediaStore.Audio.Playlists._ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#DISPLAY_NAME}
	 */
	public String displayName() {
		return getString(MediaStore.Audio.Media.DISPLAY_NAME);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#DATA}
	 */
	public String data() {
		return getString(MediaStore.Audio.Media.DATA);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#DATE_ADDED}
	 */
	public long createdAt() {
		return getLong(MediaStore.Audio.Media.DATE_ADDED);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#DATE_MODIFIED}
	 */
	public long updatedAt() {
		return getLong(MediaStore.Audio.Media.DATE_MODIFIED);
	}
}
