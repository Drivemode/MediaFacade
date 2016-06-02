package com.drivemode.media.video;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.drivemode.media.common.SortOrder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Facade object to the modularized Bucket, Video and Thumbnails database.
 * @author KeithYokoma
 */
@SuppressWarnings("unused") // public API
public class VideoFacade {
	private static VideoFacade instance;
	private final Bucket bucket;
	private final Video video;
	private final Thumbnail thumbnail;

	/**
	 * Initialize the facade with the application context.
	 * Do not call this method directly.
	 *
	 * @param context the application context
	 */
	protected VideoFacade(Context context) {
		this(new Bucket(context), new Video(context), new Thumbnail(context));
	}

	/**
	 * Convenient constructor to inject each modular class, especially for testing purpose.
	 * @param bucket the bucket class
	 * @param video the video class
	 * @param thumbnail the thumbnail class
	 */
	protected VideoFacade(Bucket bucket, Video video, Thumbnail thumbnail) {
		this.bucket = bucket;
		this.video = video;
		this.thumbnail = thumbnail;
	}

	/**
	 * Get an singleton object of {@link VideoFacade}.
	 * @param context the context.
	 * @return the singleton object of {@link VideoFacade}.
	 */
	public static VideoFacade getInstance(Context context) {
		if (instance == null)
			instance = new VideoFacade(context.getApplicationContext());
		return instance;
	}

	/**
	 * @return Modular class for Bucket.
	 */
	public Bucket bucket() {
		return bucket;
	}

	/**
	 * @return Modular class for Video.
	 */
	public Video video() {
		return video;
	}

	/**
	 * @return Modular class for Thumbnail.
	 */
	public Thumbnail thumbnail() {
		return thumbnail;
	}

	/**
	 * {@link Bucket} provides access to the bucket, the group unit of video.
	 */
	public static class Bucket {
		private static final String[] BUCKET_PROJECTION = {
				MediaStore.Video.Media.BUCKET_ID,
				MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
				MediaStore.Video.Media._ID
		};
		private static final String BUCKET_GROUP_BY = "1) GROUP BY 1,(2";
		private final Context context;
		private final ContentResolver resolver;

		protected Bucket(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		/**
		 * Fetches all buckets.
		 */
		public @Nullable VideoCursor fetch() {
			return fetch(SortOrder.UNSPECIFIED);
		}

		/**
		 * Fetches all buckets in the specified {@link SortOrder}.
		 */
		public @Nullable VideoCursor fetch(SortOrder order) {
			return new VideoCursor(resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, BUCKET_PROJECTION, BUCKET_GROUP_BY, null, order.toSql()));
		}
	}

	/**
	 * Small module class to get video metadata from {@link MediaStore}.
	 */
	public static class Video {
		private final Context context;
		private final ContentResolver resolver;

		protected Video(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		/**
		 * Fetch all video metadata from {@link MediaStore}.
		 */
		public @Nullable VideoCursor fetch() {
			return fetch(SortOrder.UNSPECIFIED);
		}

		/**
		 * Fetch all video metadata from {@link MediaStore} in the specified {@link SortOrder}.
		 */
		public @Nullable VideoCursor fetch(SortOrder order) {
			return new VideoCursor(resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, order.toSql()));
		}

		/**
		 * Fetch video metadata in the specified bucket from {@link MediaStore}.
		 */
		public @Nullable VideoCursor fetchByBucket(long bucketId) {
			return fetchByBucket(bucketId, SortOrder.UNSPECIFIED);
		}

		/**
		 * Fetch video metadata belongs to the specified bucket from {@link MediaStore} in the specified {@link SortOrder}.
		 */
		public @Nullable VideoCursor fetchByBucket(long bucketId, SortOrder order) {
			return new VideoCursor(resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null,
					MediaStore.Video.Media.BUCKET_ID + " = ?", new String[]{String.valueOf(bucketId)}, order.toSql()));
		}
	}

	/**
	 * Small module class to get video thumbnail in bitmap format.
	 */
	public static class Thumbnail {
		private final Context context;
		private final ContentResolver resolver;

		protected Thumbnail(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		/**
		 * Create a {@link Bitmap} for the video in a size specified by kind.
		 * @param videoId the video id to generate the thumbnail
		 * @param kind one of {@link android.provider.MediaStore.Video.Thumbnails#MINI_KIND}, {@link android.provider.MediaStore.Video.Thumbnails#MICRO_KIND} or {@link android.provider.MediaStore.Video.Thumbnails#FULL_SCREEN_KIND}
		 * @return the thumbnail bitmap.
		 */
		public @Nullable Bitmap fetch(long videoId, @Kind int kind) {
			return fetch(videoId, kind, null);
		}

		/**
		 * Create a {@link Bitmap} for the video with options in a size specified by kind.
		 * @param videoId the video id to generate the thumbnail
		 * @param kind one of {@link android.provider.MediaStore.Video.Thumbnails#MINI_KIND}, {@link android.provider.MediaStore.Video.Thumbnails#MICRO_KIND} or {@link android.provider.MediaStore.Video.Thumbnails#FULL_SCREEN_KIND}
		 * @param ops bitmap options
		 * @return the thumbnail bitmap.
		 */
		public @Nullable Bitmap fetch(long videoId, @Kind int kind, @Nullable BitmapFactory.Options ops) {
			return MediaStore.Video.Thumbnails.getThumbnail(resolver, videoId, kind, ops);
		}

		@Retention(RetentionPolicy.SOURCE)
		@IntDef({MediaStore.Video.Thumbnails.MINI_KIND, MediaStore.Video.Thumbnails.MICRO_KIND, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND})
		public @interface Kind {}
	}
}
