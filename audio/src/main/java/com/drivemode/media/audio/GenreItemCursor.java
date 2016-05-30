package com.drivemode.media.audio;

import android.database.Cursor;
import android.provider.MediaStore;

import com.drivemode.media.common.BaseCursor;

/**
 * @author KeishinYokomaku
 */
@SuppressWarnings("unused") // public API
public class GenreItemCursor extends BaseCursor {
	public GenreItemCursor(Cursor cursor) {
		super(cursor);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres#_ID}
	 */
	public long id() {
		return getLong(MediaStore.Audio.Genres._ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#AUDIO_ID}
	 */
	public long audioId() {
		return getLong(MediaStore.Audio.Genres.Members.AUDIO_ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#GENRE_ID}
	 */
	public long genreId() {
		return getLong(MediaStore.Audio.Genres.Members.GENRE_ID);
	}
	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#DISPLAY_NAME}
	 */
	public String displayName() {
		return getString(MediaStore.Audio.Genres.Members.DISPLAY_NAME);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#DATA}
	 */
	public String data() {
		return getString(MediaStore.Audio.Genres.Members.DATA);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#SIZE}
	 */
	public long size() {
		return getInt(MediaStore.Audio.Genres.Members.SIZE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#MIME_TYPE}
	 */
	public String mimeType() {
		return getString(MediaStore.Audio.Genres.Members.MIME_TYPE);
	}

	public boolean drmProtected() {
		return getInt("is_drm") != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#TITLE}
	 */
	public String title() {
		return getString(MediaStore.Audio.Genres.Members.TITLE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#TITLE_KEY}
	 */
	public String titleKey() {
		return getString(MediaStore.Audio.Genres.Members.TITLE_KEY);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#DURATION}
	 */
	public long duration() {
		return getLong(MediaStore.Audio.Genres.Members.DURATION);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#ARTIST}
	 */
	public String artistName() {
		return getString(MediaStore.Audio.Genres.Members.ARTIST);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#ARTIST_ID}
	 */
	public long artistId() {
		return getLong(MediaStore.Audio.Genres.Members.ARTIST_ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#ARTIST_KEY}
	 */
	public String artistKey() {
		return getString(MediaStore.Audio.Genres.Members.ARTIST_KEY);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#COMPOSER}
	 */
	public String composer() {
		return getString(MediaStore.Audio.Genres.Members.COMPOSER);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#TRACK}
	 */
	public int tracksCount() {
		return getInt(MediaStore.Audio.Genres.Members.TRACK);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#YEAR}
	 */
	public int year() {
		return getInt(MediaStore.Audio.Genres.Members.YEAR);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#IS_RINGTONE}
	 */
	public boolean isRingtone() {
		return getInt(MediaStore.Audio.Genres.Members.IS_RINGTONE) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#IS_MUSIC}
	 */
	public boolean isMusic() {
		return getInt(MediaStore.Audio.Genres.Members.IS_MUSIC) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#IS_ALARM}
	 */
	public boolean isAlarm() {
		return getInt(MediaStore.Audio.Genres.Members.IS_ALARM) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#IS_NOTIFICATION}
	 */
	public boolean isNotification() {
		return getInt(MediaStore.Audio.Genres.Members.IS_NOTIFICATION) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#IS_PODCAST}
	 */
	public boolean isPodcast() {
		return getInt(MediaStore.Audio.Genres.Members.IS_PODCAST) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#BOOKMARK}
	 */
	public long bookmarkedPosition() {
		return getLong(MediaStore.Audio.Genres.Members.BOOKMARK);
	}

	public String albumArtist() {
		return getString("album_artist");
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#ALBUM}
	 */
	public String albumName() {
		return getString(MediaStore.Audio.Genres.Members.ALBUM);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#ALBUM_ID}
	 */
	public long albumId() {
		return getLong(MediaStore.Audio.Genres.Members.ALBUM_ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#ALBUM_KEY}
	 */
	public String albumKey() {
		return getString(MediaStore.Audio.Genres.Members.ALBUM_KEY);
	}


	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#DATE_ADDED}
	 */
	public long createdAt() {
		return getLong(MediaStore.Audio.Genres.Members.DATE_ADDED);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Genres.Members#DATE_MODIFIED}
	 */
	public long updatedAt() {
		return getLong(MediaStore.Audio.Genres.Members.DATE_MODIFIED);
	}
}
