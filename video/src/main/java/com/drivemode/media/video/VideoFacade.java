package com.drivemode.media.video;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.drivemode.media.common.SortOrder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author KeithYokoma
 */
@SuppressWarnings("unused") // public API
public class VideoFacade {
	private static VideoFacade instance;
	private final Bucket bucket;
	private final Video video;
	private final Thumbnail thumbnail;

	protected VideoFacade(Context context) {
		this(new Bucket(context), new Video(context), new Thumbnail(context));
	}

	protected VideoFacade(Bucket bucket, Video video, Thumbnail thumbnail) {
		this.bucket = bucket;
		this.video = video;
		this.thumbnail = thumbnail;
	}

	public static VideoFacade getInstance(Context context) {
		if (instance == null)
			instance = new VideoFacade(context.getApplicationContext());
		return instance;
	}

	public Bucket bucket() {
		return bucket;
	}

	public Video video() {
		return video;
	}

	public Thumbnail thumbnail() {
		return thumbnail;
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

		public @Nullable Cursor fetch() {
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

		public @Nullable VideoCursor fetch() {
			return fetch(SortOrder.UNSPECIFIED);
		}

		public @Nullable VideoCursor fetch(SortOrder order) {
			return new VideoCursor(resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, order.toSql()));
		}

		public @Nullable VideoCursor fetchByBucket(long bucketId) {
			return fetchByBucket(bucketId, SortOrder.UNSPECIFIED);
		}

		public @Nullable VideoCursor fetchByBucket(long bucketId, SortOrder order) {
			return new VideoCursor(resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null,
					MediaStore.Video.Media.BUCKET_ID + " = ?", new String[]{String.valueOf(bucketId)}, order.toSql()));
		}
	}

	public static class Thumbnail {
		private final Context context;
		private final ContentResolver resolver;

		public Thumbnail(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		public @Nullable Bitmap fetch(long videoId, @Kind int kind) {
			return fetch(videoId, kind, null);
		}

		public @Nullable Bitmap fetch(long videoId, @Kind int kind, @Nullable BitmapFactory.Options ops) {
			return MediaStore.Video.Thumbnails.getThumbnail(resolver, videoId, kind, ops);
		}

		@Retention(RetentionPolicy.SOURCE)
		@IntDef({MediaStore.Video.Thumbnails.MINI_KIND, MediaStore.Video.Thumbnails.MICRO_KIND, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND})
		public @interface Kind {}
	}
}
