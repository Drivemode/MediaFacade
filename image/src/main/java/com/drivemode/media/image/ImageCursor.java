package com.drivemode.media.image;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;

import com.drivemode.media.common.BaseCursor;

/**
 * @author KeishinYokomaku
 */
@SuppressWarnings("unused") // public API
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

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#DISPLAY_NAME}
	 */
	public String displayName() {
		return getString(MediaStore.Images.Media.DISPLAY_NAME);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#DATA}
	 */
	public String data() {
		return getString(MediaStore.Images.Media.DATA);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#SIZE}
	 */
	public long size() {
		return getLong(MediaStore.Images.Media.SIZE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#MIME_TYPE}
	 */
	public String mimeType() {
		return getString(MediaStore.Images.Media.MIME_TYPE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#TITLE}
	 */
	public String title() {
		return getString(MediaStore.Images.Media.TITLE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#DESCRIPTION}
	 */
	public String description() {
		return getString(MediaStore.Images.Media.DESCRIPTION);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#PICASA_ID}
	 */
	public String picasaId() {
		return getString(MediaStore.Images.Media.PICASA_ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#IS_PRIVATE}
	 */
	public boolean isPrivate() {
		return getInt(MediaStore.Images.Media.IS_PRIVATE) != 0;
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#LATITUDE}
	 */
	public double latitude() {
		return getDouble(MediaStore.Images.Media.LATITUDE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#LONGITUDE}
	 */
	public double longitude() {
		return getDouble(MediaStore.Images.Media.LONGITUDE);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#ORIENTATION}
	 */
	public int orientation() {
		return getInt(MediaStore.Images.Media.ORIENTATION);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#MINI_THUMB_MAGIC}
	 */
	public int miniThumbId() {
		return getInt(MediaStore.Images.Media.MINI_THUMB_MAGIC);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#BUCKET_ID}
	 */
	public String bucketId() {
		return getString(MediaStore.Images.Media.BUCKET_ID);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#BUCKET_DISPLAY_NAME}
	 */
	public String bucketName() {
		return getString(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#WIDTH}
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public long width() {
		return getLong(MediaStore.Images.Media.WIDTH);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#HEIGHT}
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public long height() {
		return getLong(MediaStore.Images.Media.HEIGHT);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#DATE_TAKEN}
	 */
	public long takenAt() {
		return getLong(MediaStore.Images.Media.DATE_TAKEN);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#DATE_ADDED}
	 */
	public long createdAt() {
		return getLong(MediaStore.Images.Media.DATE_ADDED);
	}

	/**
	 * @return Data for {@link android.provider.MediaStore.Images.Media#DATE_MODIFIED}
	 */
	public long updatedAt() {
		return getLong(MediaStore.Images.Media.DATE_MODIFIED);
	}
}
