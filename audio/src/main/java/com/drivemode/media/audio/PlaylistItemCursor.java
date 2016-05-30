package com.drivemode.media.audio;

import android.database.Cursor;
import android.provider.MediaStore;

import com.drivemode.media.common.BaseCursor;

/**
 * @author KeithYokoma
 */
@SuppressWarnings("unused") // public API
public class PlaylistItemCursor extends BaseCursor {
	public PlaylistItemCursor(Cursor cursor) {
		super(cursor);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#_ID}
	 */
	public long id() {
		return getLong(MediaStore.Audio.Playlists.Members._ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#AUDIO_ID}
	 */
	public long audioId() {
		return getLong(MediaStore.Audio.Playlists.Members.AUDIO_ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#PLAYLIST_ID}
	 */
	public long playlistId() {
		return getLong(MediaStore.Audio.Playlists.Members.PLAYLIST_ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#PLAY_ORDER}
	 */
	public int playOrder() {
		return getInt(MediaStore.Audio.Playlists.Members.PLAY_ORDER);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#DISPLAY_NAME}
	 */
	public String displayName() {
		return getString(MediaStore.Audio.Playlists.Members.DISPLAY_NAME);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#DATA}
	 */
	public String data() {
		return getString(MediaStore.Audio.Playlists.Members.DATA);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#SIZE}
	 */
	public long size() {
		return getInt(MediaStore.Audio.Playlists.Members.SIZE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#MIME_TYPE}
	 */
	public String mimeType() {
		return getString(MediaStore.Audio.Playlists.Members.MIME_TYPE);
	}

	public boolean drmProtected() {
		return getInt("is_drm") != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#TITLE}
	 */
	public String title() {
		return getString(MediaStore.Audio.Playlists.Members.TITLE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#TITLE_KEY}
	 */
	public String titleKey() {
		return getString(MediaStore.Audio.Playlists.Members.TITLE_KEY);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#DURATION}
	 */
	public long duration() {
		return getLong(MediaStore.Audio.Playlists.Members.DURATION);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#COMPOSER}
	 */
	public String composer() {
		return getString(MediaStore.Audio.Playlists.Members.COMPOSER);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#ARTIST}
	 */
	public String artistName() {
		return getString(MediaStore.Audio.Playlists.Members.ARTIST);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#ARTIST_ID}
	 */
	public long artistId() {
		return getLong(MediaStore.Audio.Playlists.Members.ARTIST_ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#ARTIST_KEY}
	 */
	public String artistKey() {
		return getString(MediaStore.Audio.Playlists.Members.ARTIST_KEY);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#TRACK}
	 */
	public int tracksCount() {
		return getInt(MediaStore.Audio.Playlists.Members.TRACK);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#YEAR}
	 */
	public int year() {
		return getInt(MediaStore.Audio.Playlists.Members.YEAR);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#IS_RINGTONE}
	 */
	public boolean isRingtone() {
		return getInt(MediaStore.Audio.Playlists.Members.IS_RINGTONE) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#IS_MUSIC}
	 */
	public boolean isMusic() {
		return getInt(MediaStore.Audio.Playlists.Members.IS_MUSIC) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#IS_ALARM}
	 */
	public boolean isAlarm() {
		return getInt(MediaStore.Audio.Playlists.Members.IS_ALARM) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#IS_NOTIFICATION}
	 */
	public boolean isNotification() {
		return getInt(MediaStore.Audio.Playlists.Members.IS_NOTIFICATION) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#IS_PODCAST}
	 */
	public boolean isPodcast() {
		return getInt(MediaStore.Audio.Playlists.Members.IS_PODCAST) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#BOOKMARK}
	 */
	public long bookmarkedPosition() {
		return getLong(MediaStore.Audio.Playlists.Members.BOOKMARK);
	}

	public String albumArtist() {
		return getString("album_artist");
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#ALBUM}
	 */
	public String albumName() {
		return getString(MediaStore.Audio.Playlists.Members.ALBUM);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#ALBUM_ID}
	 */
	public long albumId() {
		return getLong(MediaStore.Audio.Playlists.Members.ALBUM_ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#ALBUM_KEY}
	 */
	public String albumKey() {
		return getString(MediaStore.Audio.Playlists.Members.ALBUM_KEY);
	}


	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#DATE_ADDED}
	 */
	public long createdAt() {
		return getLong(MediaStore.Audio.Playlists.Members.DATE_ADDED);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Audio.Playlists.Members#DATE_MODIFIED}
	 */
	public long updatedAt() {
		return getLong(MediaStore.Audio.Playlists.Members.DATE_MODIFIED);
	}
}
