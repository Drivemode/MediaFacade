package com.drivemode.media.audio;

import android.database.Cursor;
import android.provider.MediaStore;

import com.drivemode.media.common.BaseCursor;

/**
 * @author KeishinYokomaku
 */
@SuppressWarnings("unused") // public API
public class AlbumItemCursor extends BaseCursor {
	public static final String TAG = AlbumItemCursor.class.getSimpleName();

	public AlbumItemCursor(Cursor cursor) {
		super(cursor);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Albums#_ID}
	 */
	public long id() {
		return getLong(MediaStore.Audio.Albums._ID);
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
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#SIZE}
	 */
	public long size() {
		return getInt(MediaStore.Audio.Media.SIZE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#MIME_TYPE}
	 */
	public String mimeType() {
		return getString(MediaStore.Audio.Media.MIME_TYPE);
	}

	public boolean drmProtected() {
		return getInt("is_drm") != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#TITLE}
	 */
	public String title() {
		return getString(MediaStore.Audio.Media.TITLE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#TITLE_KEY}
	 */
	public String titleKey() {
		return getString(MediaStore.Audio.Media.TITLE_KEY);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#DURATION}
	 */
	public long duration() {
		return getLong(MediaStore.Audio.Media.DURATION);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#ARTIST}
	 */
	public String artistName() {
		return getString(MediaStore.Audio.Media.ARTIST);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#ARTIST_ID}
	 */
	public long artistId() {
		return getLong(MediaStore.Audio.Media.ARTIST_ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#ARTIST_KEY}
	 */
	public String artistKey() {
		return getString(MediaStore.Audio.Media.ARTIST_KEY);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#COMPOSER}
	 */
	public String composer() {
		return getString(MediaStore.Audio.Media.COMPOSER);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#TRACK}
	 */
	public int tracksCount() {
		return getInt(MediaStore.Audio.Media.TRACK);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#YEAR}
	 */
	public int year() {
		return getInt(MediaStore.Audio.Media.YEAR);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#IS_RINGTONE}
	 */
	public boolean isRingtone() {
		return getInt(MediaStore.Audio.Media.IS_RINGTONE) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#IS_MUSIC}
	 */
	public boolean isMusic() {
		return getInt(MediaStore.Audio.Media.IS_MUSIC) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#IS_ALARM}
	 */
	public boolean isAlarm() {
		return getInt(MediaStore.Audio.Media.IS_ALARM) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#IS_NOTIFICATION}
	 */
	public boolean isNotification() {
		return getInt(MediaStore.Audio.Media.IS_NOTIFICATION) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#IS_PODCAST}
	 */
	public boolean isPodcast() {
		return getInt(MediaStore.Audio.Media.IS_PODCAST) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#BOOKMARK}
	 */
	public long bookmarkedPosition() {
		return getLong(MediaStore.Audio.Media.BOOKMARK);
	}

	public String albumArtist() {
		return getString("album_artist");
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#ALBUM}
	 */
	public String albumName() {
		return getString(MediaStore.Audio.Media.ALBUM);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#ALBUM_ID}
	 */
	public long albumId() {
		return getLong(MediaStore.Audio.Media.ALBUM_ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Media#ALBUM_KEY}
	 */
	public String albumKey() {
		return getString(MediaStore.Audio.Media.ALBUM_KEY);
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
