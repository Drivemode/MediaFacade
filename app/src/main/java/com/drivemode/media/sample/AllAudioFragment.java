package com.drivemode.media.sample;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivemode.media.audio.AudioFacade;
import com.drivemode.media.common.CursorUtils;
import com.drivemode.media.sample.databinding.FragmentAudioAllBinding;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author KeithYokoma
 */
public class AllAudioFragment extends Fragment {
	private FragmentAudioAllBinding binding;
	private CursorAdapter adapter;
	private AudioFacade facade;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_audio_all, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		binding = FragmentAudioAllBinding.bind(getView());
		facade = AudioFacade.getInstance(getContext());

		adapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_2, null,
				new String[] {MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.ARTIST},
				new int[] {android.R.id.text1, android.R.id.text2}, 0);
		binding.list.setEmptyView(binding.empty);
		binding.list.setAdapter(adapter);
		Observable.fromCallable(() -> facade.media().fetch())
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
