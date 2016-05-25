package com.drivemode.media.video;

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
public class VideoFacade {
	private final Bucket bucket;
	private final Video video;

	public VideoFacade(Context context) {
		this(new Bucket(context), new Video(context));
	}

	public VideoFacade(Bucket bucket, Video video) {
		this.bucket = bucket;
		this.video = video;
	}

	public Bucket bucket() {
		return bucket;
	}

	public Video video() {
		return video;
	}

	public static class Bucket {
		private static final String[] BUCKET_PROJECTION = {
				MediaStore.Video.Media.BUCKET_ID,
				MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
				MediaStore.Video.Media._ID
		};
		private static final String BUCKET_GROUP_BY = "1) GROUP BY 1,(2";
		private final Context context;
		private final ContentResolver resolver;

		public Bucket(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		public @Nullable
		Cursor fetch() {
			return fetch(SortOrder.UNSPECIFIED);
		}

		public @Nullable Cursor fetch(SortOrder order) {
			return resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, BUCKET_PROJECTION, BUCKET_GROUP_BY, null, order.toSql());
		}
	}

	public static class Video {
		private final Context context;
		private final ContentResolver resolver;

		public Video(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		public @Nullable Cursor fetch() {
			return fetch(SortOrder.UNSPECIFIED);
		}

		public @Nullable Cursor fetch(SortOrder order) {
			return resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, order.toSql());
		}

		public @Nullable Cursor fetchByBucket(long bucketId) {
			return fetchByBucket(bucketId, SortOrder.UNSPECIFIED);
		}

		public @Nullable Cursor fetchByBucket(long bucketId, SortOrder order) {
			return resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null,
					MediaStore.Video.Media.BUCKET_ID + " = ?", new String[]{String.valueOf(bucketId)}, order.toSql());
		}
	}
}
