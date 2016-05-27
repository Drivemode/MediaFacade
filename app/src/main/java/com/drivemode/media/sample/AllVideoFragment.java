package com.drivemode.media.sample;

import android.content.Context;
import android.database.Cursor;
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

import com.drivemode.media.common.CursorUtils;
import com.drivemode.media.sample.databinding.FragmentVideoAllBinding;
import com.drivemode.media.video.VideoCursor;
import com.drivemode.media.video.VideoFacade;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author KeishinYokomaku
 */
public class AllVideoFragment extends Fragment {
	private FragmentVideoAllBinding binding;
	private CursorAdapter adapter;
	private VideoFacade facade;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_video_all, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		binding = FragmentVideoAllBinding.bind(getView());
		facade = VideoFacade.getInstance(getContext());

		adapter = new SimpleCursorAdapter(getContext(), R.layout.list_item_image, null,
				new String[] {MediaStore.Images.Media.BUCKET_DISPLAY_NAME},
				new int[] {R.id.text}, 0) {
			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				super.bindView(view, context, cursor);
				VideoCursor wrapper = new VideoCursor(cursor);
				ImageView iv = (ImageView) view.findViewById(R.id.image);
				iv.setImageBitmap(facade.thumbnail().fetch(wrapper.id(), MediaStore.Video.Thumbnails.MINI_KIND));
			}
		};
		binding.list.setEmptyView(binding.empty);
		binding.list.setAdapter(adapter);
		Observable.fromCallable(() -> facade.video().fetch())
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
