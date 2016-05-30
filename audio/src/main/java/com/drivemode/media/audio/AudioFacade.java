package com.drivemode.media.audio;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.drivemode.media.common.CursorUtils;
import com.drivemode.media.common.SortOrder;


/**
 * @author KeithYokoma
 */
@SuppressWarnings("unused") // public API
public class AudioFacade {
	private static AudioFacade instance;
	private final Media media;
	private final Playlist playlist;
	private final Album album;
	private final Genre genre;
	private final Artist artist;

	protected AudioFacade(@NonNull Context context) {
		this(new Media(context), new Playlist(context), new Album(context), new Genre(context), new Artist(context));
	}

	protected AudioFacade(@NonNull Media media,
						  @NonNull Playlist playlist,
						  @NonNull Album album,
						  @NonNull Genre genre,
						  @NonNull Artist artist) {
		this.media = media;
		this.playlist = playlist;
		this.album = album;
		this.genre = genre;
		this.artist = artist;
	}

	public static AudioFacade getInstance(Context context) {
		if (instance == null)
			instance = new AudioFacade(context.getApplicationContext());
		return instance;
	}

	public @NonNull Playlist playlist() {
		return playlist;
	}

	public @NonNull Album album() {
		return album;
	}

	public @NonNull Genre genre() {
		return genre;
	}

	public @NonNull Artist artist() {
		return artist;
	}

	public @NonNull Media media() {
		return media;
	}

	public static class Media {
		private final Context context;
		private final ContentResolver resolver;

		public Media(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		public @Nullable MediaItemCursor fetch() {
			return fetch(SortOrder.UNSPECIFIED);
		}

		public @Nullable MediaItemCursor fetch(SortOrder order) {
			return new MediaItemCursor(resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, order.toSql()));
		}
	}

	public static class Playlist {
		private final Context context;
		private final ContentResolver resolver;

		public Playlist(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		public @Nullable PlaylistCursor fetchLists() {
			return fetchLists(SortOrder.UNSPECIFIED);
		}

		public @Nullable PlaylistCursor fetchLists(SortOrder order) {
			return new PlaylistCursor(resolver.query(
					MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, null, null, null, order.toSql()));
		}

		public @Nullable PlaylistItemCursor fetchPlayableItems(long playlistId) {
			return fetchPlayableItems(playlistId, SortOrder.UNSPECIFIED);
		}

		public @Nullable PlaylistItemCursor fetchPlayableItems(long playlistId, SortOrder order) {
			return new PlaylistItemCursor(resolver.query(
					MediaStore.Audio.Playlists.Members.getContentUri("external", playlistId), null, null, null, order.toSql()));
		}

		public @Nullable Uri createNew(String name) {
			ContentValues value = new ContentValues();
			value.put(MediaStore.Audio.Playlists.NAME, name);
			return resolver.insert(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, value);
		}

		public int getLastPlayOrder(long playlistId) {
			Cursor cursor = null;
			try {
				cursor = fetchPlayableItems(playlistId, new SortOrder(MediaStore.Audio.Playlists.Members.PLAY_ORDER, SortOrder.Order.ASCENDING));
				if (cursor == null || !cursor.moveToLast())
					return 0;
				return cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.PLAY_ORDER));
			} finally {
				CursorUtils.close(cursor);
			}
		}

		public int updateName(long playlistId, String name) {
			ContentValues value = new ContentValues();
			value.put(MediaStore.Audio.Playlists.NAME, name);
			return resolver.update(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, value,
					MediaStore.Audio.Playlists._ID + " = ?", new String[] {String.valueOf(playlistId)});
		}

		public int remove(long playlistId) {
			return resolver.delete(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI,
					MediaStore.Audio.Playlists._ID + " = ?", new String[]{String.valueOf(playlistId)});
		}

		public @Nullable Uri insertItemTo(long playlistId, long audioId, int position) {
			ContentValues value = new ContentValues();
			value.put(MediaStore.Audio.Playlists.Members.AUDIO_ID, audioId);
			value.put(MediaStore.Audio.Playlists.Members.PLAY_ORDER, position);
			return resolver.insert(MediaStore.Audio.Playlists.Members.getContentUri("external", playlistId), value);
		}

		public int insertItemsTo(long playlistId, @NonNull long[] audioIds) {
			int[] positions = new int[audioIds.length];
			for (int i = 0, limit = audioIds.length; i < limit; i++) {
				positions[i] = i;
			}
			return insertItemsTo(playlistId, audioIds, positions);
		}

		public int insertItemsTo(long playlistId, @NonNull long[] audioIds, @NonNull int[] positions) {
			if (audioIds.length != positions.length)
				throw new IllegalArgumentException("audio id length does not match corresponding position array length");
			ContentValues[] values = new ContentValues[audioIds.length];
			for (int i = 0, limit = audioIds.length; i < limit; i++) {
				ContentValues value = new ContentValues();
				value.put(MediaStore.Audio.Playlists.Members.AUDIO_ID, audioIds[i]);
				value.put(MediaStore.Audio.Playlists.Members.PLAY_ORDER, positions[i]);
				values[i] = value;
			}
			return resolver.bulkInsert(MediaStore.Audio.Playlists.Members.getContentUri("external", playlistId), values);
		}

		public int removeItemFrom(long playlistId, long audioId) {
			return resolver.delete(MediaStore.Audio.Playlists.Members.getContentUri("external", playlistId),
					MediaStore.Audio.Playlists.Members.AUDIO_ID + " = ?", new String[]{String.valueOf(audioId)});
		}
	}

	public static class Album {
		private static final Uri ALBUM_ART_URI_BASE = Uri.parse("content://media/external/audio/albumart");
		private final Context context;
		private final ContentResolver resolver;

		public Album(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		public @Nullable AlbumCursor fetchAlbums() {
			return fetchAlbums(SortOrder.UNSPECIFIED);
		}

		public @Nullable AlbumCursor fetchAlbums(SortOrder order) {
			return new AlbumCursor(resolver.query(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null, order.toSql()));
		}

		public @Nullable AlbumItemCursor fetchPlayableItems(long albumId) {
			return fetchPlayableItems(albumId, SortOrder.UNSPECIFIED);
		}

		public @Nullable AlbumItemCursor fetchPlayableItems(long albumId, SortOrder order) {
			return new AlbumItemCursor(resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Audio.Media.ALBUM_ID + " = ?", new String[]{String.valueOf(albumId)}, order.toSql()));
		}

		public @NonNull Uri albumArtUri(long albumId) {
			return ContentUris.withAppendedId(ALBUM_ART_URI_BASE, albumId);
		}
	}

	public static class Genre {
		private final Context context;
		private final ContentResolver resolver;

		public Genre(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		public @Nullable GenreCursor fetchGenres() {
			return fetchGenres(SortOrder.UNSPECIFIED);
		}

		public @Nullable GenreCursor fetchGenres(SortOrder order) {
			return new GenreCursor(resolver.query(MediaStore.Audio.Genres.EXTERNAL_CONTENT_URI, null, null, null, order.toSql()));
		}

		public @Nullable GenreItemCursor fetchPlayableItems(long genreId) {
			return fetchPlayableItems(genreId, SortOrder.UNSPECIFIED);
		}

		public @Nullable GenreItemCursor fetchPlayableItems(long genreId, SortOrder order) {
			return new GenreItemCursor(resolver.query(MediaStore.Audio.Genres.Members.getContentUri("external", genreId), null, null, null, order.toSql()));
		}
	}

	public static class Artist {
		private final Context context;
		private final ContentResolver resolver;

		public Artist(Context context) {
			this.context = context;
			this.resolver = context.getContentResolver();
		}

		public @Nullable ArtistCursor fetchArtists() {
			return fetchArtists(SortOrder.UNSPECIFIED);
		}

		public @Nullable ArtistCursor fetchArtists(SortOrder order) {
			return new ArtistCursor(resolver.query(MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI, null, null, null, order.toSql()));
		}

		public @Nullable ArtistItemCursor fetchAlbums(long artistId) {
			return fetchAlbums(artistId, SortOrder.UNSPECIFIED);
		}

		public @Nullable ArtistItemCursor fetchAlbums(long artistId, SortOrder order) {
			return new ArtistItemCursor(resolver.query(MediaStore.Audio.Artists.Albums.getContentUri("external", artistId), null, null, null, order.toSql()));
		}
	}
}
