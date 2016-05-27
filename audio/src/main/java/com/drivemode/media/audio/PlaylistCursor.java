package com.drivemode.media.audio;

import android.database.Cursor;
import android.provider.MediaStore;

import com.drivemode.media.common.BaseCursor;

/**
 * @author KeithYokoma
 */
public class PlaylistCursor extends BaseCursor {
	public PlaylistCursor(Cursor cursor) {
		super(cursor);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists#_ID}
	 */
	public long id() {
		return getLong(MediaStore.Audio.Playlists._ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists#NAME}
	 */
	public String name() {
		return getString(MediaStore.Audio.Playlists.NAME);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists#DATA}
	 */
	public String data() {
		return getString(MediaStore.Audio.Playlists.DATA);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists#DATE_ADDED}
	 */
	public long createdAt() {
		return getLong(MediaStore.Audio.Playlists.DATE_ADDED);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists#DATE_MODIFIED}
	 */
	public long updatedAt() {
		return getLong(MediaStore.Audio.Playlists.DATE_MODIFIED);
	}
}
