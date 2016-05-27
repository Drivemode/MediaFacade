package com.drivemode.media.sample;

import android.content.ContentUris;
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
import com.drivemode.media.image.ImageCursor;
import com.drivemode.media.image.ImageFacade;
import com.drivemode.media.sample.databinding.FragmentImageAllBinding;
import com.squareup.picasso.Picasso;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author KeishinYokomaku
 */
public class AllImageFragment extends Fragment {
	private FragmentImageAllBinding binding;
	private CursorAdapter adapter;
	private ImageFacade facade;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_image_all, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		binding = FragmentImageAllBinding.bind(getView());
		facade = ImageFacade.getInstance(getContext());

		adapter = new SimpleCursorAdapter(getContext(), R.layout.list_item_image, null,
				new String[] {MediaStore.Images.Media.BUCKET_DISPLAY_NAME},
				new int[] {R.id.text}, 0) {
			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				super.bindView(view, context, cursor);
				ImageCursor wrapper = new ImageCursor(cursor);
				ImageView iv = (ImageView) view.findViewById(R.id.image);
				long id = wrapper.id();
				Picasso.with(context).load(ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id))
						.resizeDimen(R.dimen.image_size, R.dimen.image_size)
						.centerCrop()
						.into(iv);
			}
		};
		binding.list.setEmptyView(binding.empty);
		binding.list.setAdapter(adapter);
		Observable.fromCallable(() -> facade.image().fetch())
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
