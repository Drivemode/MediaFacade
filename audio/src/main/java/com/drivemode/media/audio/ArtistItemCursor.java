package com.drivemode.media.audio;

import android.database.Cursor;
import android.provider.MediaStore;

import com.drivemode.media.common.BaseCursor;

/**
 * @author KeishinYokomaku
 */
@SuppressWarnings("unused") // public API
public class ArtistItemCursor extends BaseCursor {
	public static final String TAG = ArtistItemCursor.class.getSimpleName();

	public ArtistItemCursor(Cursor cursor) {
		super(cursor);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Albums#_ID}
	 */
	public long id() {
		return getLong(MediaStore.Audio.Albums._ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Artists.Albums#ALBUM}
	 */
	public String displayName() {
		return getString(MediaStore.Audio.Artists.Albums.ALBUM);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Artists.Albums#ALBUM_KEY}
	 */
	public String albumKey() {
		return getString(MediaStore.Audio.Artists.Albums.ALBUM_KEY);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Artists.Albums#ALBUM_ART}
	 */
	public String albumArtPath() {
		return getString(MediaStore.Audio.Artists.Albums.ALBUM_ART);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Artists.Albums#ARTIST}
	 */
	public String artistName() {
		return getString(MediaStore.Audio.Artists.Albums.ARTIST);
	}

	public String artistKey() {
		return getString("artist_key");
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Albums#NUMBER_OF_SONGS}
	 */
	public int songsCount() {
		return getInt(MediaStore.Audio.Artists.Albums.NUMBER_OF_SONGS);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Albums#NUMBER_OF_SONGS_FOR_ARTIST}
	 */
	public int songsCountByArtist() {
		return getInt(MediaStore.Audio.Artists.Albums.NUMBER_OF_SONGS_FOR_ARTIST);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Albums#FIRST_YEAR}
	 */
	public long firstYear() {
		return getLong(MediaStore.Audio.Albums.FIRST_YEAR);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Albums#LAST_YEAR}
	 */
	public long lastYear() {
		return getLong(MediaStore.Audio.Albums.LAST_YEAR);
	}
}
