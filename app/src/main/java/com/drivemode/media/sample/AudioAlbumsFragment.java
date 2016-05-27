package com.drivemode.media.sample;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.drivemode.media.audio.AudioFacade;
import com.drivemode.media.common.CursorUtils;
import com.drivemode.media.sample.databinding.FragmentAudioAlbumsBinding;
import com.squareup.picasso.Picasso;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author KeithYokoma
 */
public class AudioAlbumsFragment extends Fragment {
	private FragmentAudioAlbumsBinding binding;
	private CursorAdapter adapter;
	private AudioFacade facade;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_audio_albums, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		binding = FragmentAudioAlbumsBinding.bind(getView());
		facade = AudioFacade.getInstance(getContext());

		adapter = new SimpleCursorAdapter(getContext(), R.layout.list_item_album, null,
				new String[] {MediaStore.Audio.Albums.ALBUM, MediaStore.Audio.Albums.ARTIST},
				new int[] {R.id.text1, R.id.text2}, 0) {
			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				super.bindView(view, context, cursor);
				ImageView iv = (ImageView) view.findViewById(R.id.album_art);
				Uri uri = facade.album().albumArtUri(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Albums._ID)));
				Picasso.with(context).load(uri).resizeDimen(R.dimen.image_size, R.dimen.image_size).centerCrop().into(iv);
			}
		};
		binding.list.setEmptyView(binding.empty);
		binding.list.setAdapter(adapter);
		Observable.fromCallable(() -> facade.album().fetchAlbums())
				.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
				.subscribe(adapter::swapCursor, LogHelper::logError, this::applyEmptyContent);
	}

	@Override
	public void onDestroyView() {
		CursorUtils.close(adapter.swapCursor(null));
		super.onDestroyView();
	}

	private void applyEmptyContent() {
		if (adapter.isEmpty())
			binding.empty.setText(R.string.empty_view_no_content);
	}
}
