package com.drivemode.media.image;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import com.drivemode.media.common.SortOrder;

/**
 * @author KeithYokoma
 */
@SuppressWarnings("unused") // public API
public class ImageFacade {
	private final Bucket bucket;
	private final Image image;

	public ImageFacade(Context context) {
		this(new Bucket(context), new Image(context));
	}

	public Bucket bucket() {
		return bucket;
	}

	public Image image() {
		return image;
	}

	public ImageFacade(Bucket bucket, Image image) {
		this.bucket = bucket;
		this.image = image;
	}

	public static class Bucket {
		private static final String[] BUCKET_PROJECTION = {
				MediaStore.Images.Media.BUCKET_ID,
				MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
				MediaStore.Images.Media._ID
		};
		private static final String BUCKET_GROUP_BY = "1) GROUP BY 1,(2";
		private final Context context;
		private final ContentResolver resolver;

		public Bucket(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		public @Nullable Cursor fetch() {
			return fetch(SortOrder.UNSPECIFIED);
		}

		public @Nullable Cursor fetch(SortOrder order) {
			return resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, BUCKET_PROJECTION, BUCKET_GROUP_BY, null, order.toSql());
		}
	}

	public static class Image {
		private final Context context;
		private final ContentResolver resolver;

		public Image(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		public @Nullable Cursor fetch() {
			return fetch(SortOrder.UNSPECIFIED);
		}

		public @Nullable Cursor fetch(SortOrder order) {
			return resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, order.toSql());
		}

		public @Nullable Cursor fetchByBucket(long bucketId) {
			return fetchByBucket(bucketId, SortOrder.UNSPECIFIED);
		}

		public @Nullable Cursor fetchByBucket(long bucketId, SortOrder order) {
			return resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
					MediaStore.Images.Media.BUCKET_ID + " = ?", new String[]{String.valueOf(bucketId)}, order.toSql());
		}
	}
}
