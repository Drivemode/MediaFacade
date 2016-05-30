# MediaFacade

Facade modules for dealing with complicated MediaStore in a simple way.

## Usage

### Audio

`Audio` in `MediaStore` has several tables like `Media`, `Artists`, `Albums`, `Playlists` and `Genres`.
`MediaFacade` provides easy accesses to those tables.

#### Albums

```java
public class SampleActivity extends Activity {
  private AudioFacade facade;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // ...

    facade = AudioFacade.getInstance(this);
    Cursor albums = null;
    try {
      albums = facade.album().fetchAlbum();
      // do whatever you like with the album metadata
    } finally {
      if (albums != null)
        albums.close();
    }
  }
}
```

#### Artists

```java
public class SampleActivity extends Activity {
  private AudioFacade facade;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // ...

    facade = AudioFacade.getInstance(this);
    Cursor artists = null;
    try {
      artists = facade.artist().fetchArtists();
      // do whatever you like with the album metadata
    } finally {
      if (artists != null)
        artists.close();
    }
  }
}
```

#### Genres

```java
public class SampleActivity extends Activity {
  private AudioFacade facade;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // ...

    facade = AudioFacade.getInstance(this);
    Cursor genres = null;
    try {
      genres = facade.genre().fetchGenres();
      // do whatever you like with the album metadata
    } finally {
      if (genres != null)
        genres.close();
    }
  }
}
```

#### Playlists

```java
public class SampleActivity extends Activity {
  private AudioFacade facade;
  private EditText playlistName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // ...

    facade = AudioFacade.getInstance(this);
    Cursor playlists = null;
    try {
      playlists = facade.playlist().fetchGenres();
      // do whatever you like with the album metadata
    } finally {
      if (playlists != null)
        playlists.close();
    }
  }

  public void onNewPlaylistButtonClick(View v) {
    String name = playlistName.getText().toString();
    Uri uri = facade.playlist().createNew(name);
    // you can add music tracks with this uri
  }
}
```

### Image and Video

`Image` and `Video` have similar structure in their tables.
One noteworthy concept is `Bucket`, which is kind of `Album` for images and/or videos.
`Image` and `Video` have 2 columns (`BUCKET_ID` and `BUCKET_DISPLAY_NAME`) for describing which bucket the image belongs to.

#### Bucket

Since there's no specific table that manages each buckets so you need to group by bucket when you query images/videos in SQL.
`ImageFacade` and `VideoFacade` do grouping for you.

```java
public class SampleActivity extends Activity {
  private ImageFacade facade;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // ...

    facade = ImageFacade.getInstance(this);
    Cursor imageBuckets = null;
    try {
      imageBuckets = facade.bucket().fetch();
      if (imageBuckets == null)
        return;
      while (imageBuckets.moveToNext()) {
        long bucketId = imageBuckets.getLong(imageBuckets.getColumnIndex(MediaStore.Image.Media.BUCKET_ID));
        // you can query images in the specific bucket with this bucketId
        Cursor imagesInBucket = facade.image().fetchByBucket(bucketId);
        // ...
      }
    } finally {
      if (imageBuckets != null)
        imageBuckets.close();
    }
  }
}
```

### Cursors

MediaFacade provides customized yet fully Android compatible `Cursor`s for developers to access fields by just calling corresponding methods.

```java
public class SampleActivity extends Activity {
  private ImageFacade facade;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // ...

    facade = ImageFacade.getInstance(this);
    ImageCursor imageBuckets = null;
    try {
      imageBuckets = facade.bucket().fetch();
      if (imageBuckets == null)
        return;
      while (imageBuckets.moveToNext()) {
        long bucketId = imageBuckets.id();
        // you can query images in the specific bucket with this bucketId
        ImageCursor imagesInBucket = facade.image().fetchByBucket(bucketId);
        // ...
      }
    } finally {
      if (imageBuckets != null)
        imageBuckets.close();
    }
  }
}
```

We have following customized classes of `Cursor`s.

- MediaItemCursor
- AlbumCursor
- AlbumItemCursor
- GenreCursor
- GenreItemCursor
- ArtistCursor
- ArtistItemCursor
- PlaylistCurosr
- PlaylistItemCurosr
- ImageCursor
- VideoCursor

These `Cursor`s are subclasses of `Cursor` so that you can use them with `CursorAdapter` and various `Cursor` API.

## Download

Gradle:

```
compile 'com.drivemode:mediafacade-audio:0.1.0@aar'
compile 'com.drivemode:mediafacade-image:0.1.0@aar'
compile 'com.drivemode:mediafacade-video:0.1.0@aar'
```


## License

Apache v2

```
Copyright (C) 2016 Drivemode, Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use
this file except in compliance with the License. You may obtain a copy of the
License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed
under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
CONDITIONS OF ANY KIND, either express or implied. See the License for the
specific language governing permissions and limitations under the License.
```
