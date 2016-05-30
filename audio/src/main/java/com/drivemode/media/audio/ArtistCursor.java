package com.drivemode.media.audio;

import android.database.Cursor;
import android.provider.MediaStore;

import com.drivemode.media.common.BaseCursor;

/**
 * @author KeishinYokomaku
 */
@SuppressWarnings("unused")
public class ArtistCursor extends BaseCursor {
	public static final String TAG = ArtistCursor.class.getSimpleName();

	public ArtistCursor(Cursor cursor) {
		super(cursor);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Artists#_ID}
	 */
	public long id() {
		return getLong(MediaStore.Audio.Artists._ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Artists#ARTIST}
	 */
	public String displayName() {
		return getString(MediaStore.Audio.Artists.ARTIST);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Artists#ARTIST_KEY}
	 */
	public String key() {
		return getString(MediaStore.Audio.Artists.ARTIST_KEY);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Artists#NUMBER_OF_ALBUMS}
	 */
	public int albumsCount() {
		return getInt(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Artists#NUMBER_OF_TRACKS}
	 */
	public int tracksCount() {
		return getInt(MediaStore.Audio.Artists.NUMBER_OF_TRACKS);
	}
}
