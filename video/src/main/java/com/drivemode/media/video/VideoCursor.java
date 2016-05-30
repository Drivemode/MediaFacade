package com.drivemode.media.video;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;

import com.drivemode.media.common.BaseCursor;

/**
 * @author KeishinYokomaku
 */
@SuppressWarnings("unused") // public API
public class VideoCursor extends BaseCursor {
	public VideoCursor(Cursor cursor) {
		super(cursor);
	}

	/**
	 * @return Data for {@link MediaStore.Video.Media#_ID}
	 */
	public long id() {
		return getLong(MediaStore.Video.Media._ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#DISPLAY_NAME}
	 */
	public String displayName() {
		return getString(MediaStore.Video.Media.DISPLAY_NAME);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#DATA}
	 */
	public String data() {
		return getString(MediaStore.Video.Media.DATA);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#SIZE}
	 */
	public long size() {
		return getLong(MediaStore.Video.Media.SIZE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#MIME_TYPE}
	 */
	public String mimeType() {
		return getString(MediaStore.Video.Media.MIME_TYPE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#TITLE}
	 */
	public String title() {
		return getString(MediaStore.Video.Media.TITLE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#DESCRIPTION}
	 */
	public String description() {
		return getString(MediaStore.Video.Media.DESCRIPTION);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#IS_PRIVATE}
	 */
	public boolean isPrivate() {
		return getInt(MediaStore.Video.Media.IS_PRIVATE) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#LATITUDE}
	 */
	public double latitude() {
		return getDouble(MediaStore.Video.Media.LATITUDE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#LONGITUDE}
	 */
	public double longitude() {
		return getDouble(MediaStore.Video.Media.LONGITUDE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#MINI_THUMB_MAGIC}
	 */
	public int miniThumbId() {
		return getInt(MediaStore.Video.Media.MINI_THUMB_MAGIC);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#BUCKET_ID}
	 */
	public String bucketId() {
		return getString(MediaStore.Video.Media.BUCKET_ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#BUCKET_DISPLAY_NAME}
	 */
	public String bucketName() {
		return getString(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#DURATION}
	 */
	public long duration() {
		return getLong(MediaStore.Video.Media.DURATION);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#ARTIST}
	 */
	public String artist() {
		return getString(MediaStore.Video.Media.ARTIST);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#ALBUM}
	 */
	public String album() {
		return getString(MediaStore.Video.Media.ALBUM);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#RESOLUTION}
	 */
	public String resolution() {
		return getString(MediaStore.Video.Media.RESOLUTION);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#WIDTH}
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public long width() {
		return getLong(MediaStore.Video.Media.WIDTH);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#HEIGHT}
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public long height() {
		return getLong(MediaStore.Video.Media.HEIGHT);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#DATE_TAKEN}
	 */
	public long takenAt() {
		return getLong(MediaStore.Video.Media.DATE_TAKEN);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#TAGS}
	 */
	public String tags() {
		return getString(MediaStore.Video.Media.TAGS);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#CATEGORY}
	 */
	public String category() {
		return getString(MediaStore.Video.Media.CATEGORY);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#LANGUAGE}
	 */
	public String language() {
		return getString(MediaStore.Video.Media.LANGUAGE);
	}

	public String miniThumbFilePath() {
		return getString("mini_thumb_data");
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#BOOKMARK}
	 */
	public long bookmatkedPosition() {
		return getLong(MediaStore.Video.Media.BOOKMARK);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#DATE_ADDED}
	 */
	public long createdAt() {
		return getLong(MediaStore.Video.Media.DATE_ADDED);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Video.Media#DATE_MODIFIED}
	 */
	public long updatedAt() {
		return getLong(MediaStore.Video.Media.DATE_MODIFIED);
	}
}
