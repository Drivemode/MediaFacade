package com.drivemode.media.sample;

import android.Manifest;
import android.annotation.TargetApi;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.drivemode.media.sample.databinding.ActivityMainBinding;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
	private ActivityMainBinding binding;
	private MenuItem previousMenuItem;
	private Handler handler = new Handler(Looper.getMainLooper());

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		DataBindingUtil.bind(binding.navView.getHeaderView(0));

		setSupportActionBar(binding.toolbar);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		binding.drawerLayout.addDrawerListener(toggle);
		toggle.syncState();

		binding.navView.setNavigationItemSelectedListener(this);
		MainActivityPermissionsDispatcher.setUpContentWithCheck(this, savedInstanceState != null);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
	}

	@Override
	public void onBackPressed() {
		if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
			binding.drawerLayout.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		final NavigationItem navItem = NavigationItem.forMenuItem(item);

		item.setChecked(true);
		if (previousMenuItem != null) {
			previousMenuItem.setChecked(false);
		}
		previousMenuItem = item;

		new Handler().post(() -> getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, navItem.page()).commit());
		binding.drawerLayout.closeDrawer(GravityCompat.START);
		return true;
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
	public void setUpContent(boolean hasPreviousState) {
		if (hasPreviousState)
			return;
		binding.navView.setCheckedItem(R.id.nav_audio_albums);
		NavigationItem navItem = NavigationItem.AUDIO_ALBUMS;
		handler.post(() -> getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, navItem.page()).commit());
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
	public void showRationale(PermissionRequest request) {
		new AlertDialog.Builder(this)
				.setMessage(R.string.permission_dialog_access_external_storage_title)
				.setPositiveButton(R.string.permission_dialog_allow, (dialog, button) -> request.proceed())
				.setNegativeButton(R.string.permission_dialog_deny, (dialog, button) -> request.cancel())
				.show();
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
	void showDeniedForStorageAccess() {
		Snackbar.make(binding.fragmentContainer, R.string.permission_dialog_access_external_storage_denied, Snackbar.LENGTH_SHORT).show();
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
	void showNeverAskForStorageAccess() {
		Snackbar.make(binding.fragmentContainer, R.string.permission_dialog_access_external_storage_never_ask, Snackbar.LENGTH_SHORT).show();
	}

	enum NavigationItem {
		AUDIO_ALL(R.id.nav_audio_all) {
			@Override
			Fragment page() {
				return new AllAudioFragment();
			}
		},
		AUDIO_ALBUMS(R.id.nav_audio_albums) {
			@Override
			/* package */ Fragment page() {
				return new AudioAlbumsFragment();
			}
		},
		AUDIO_ARTISTS(R.id.nav_audio_artists) {
			@Override
			/* package */ Fragment page() {
				return new AudioArtistsFragment();
			}
		},
		AUDIO_GENRES(R.id.nav_audio_genres) {
			@Override
			/* package */ Fragment page() {
				return new AudioGenresFragment();
			}
		},
		AUDIO_PLAYLISTS(R.id.nav_audio_playlist) {
			@Override
			/* package */ Fragment page() {
				return new AudioPlaylistsFragment();
			}
		},
		IMAGE_ALL(R.id.nav_image_all) {
			@Override
			/* package */ Fragment page() {
				return new AllImageFragment();
			}
		},
		IMAGE_BUCKETS(R.id.nav_image_buckets) {
			@Override
			/* package */ Fragment page() {
				return new ImageBucketsFragment();
			}
		},
		VIDEO_ALL(R.id.nav_video_all) {
			@Override
			/* package */ Fragment page() {
				return new AllVideoFragment();
			}
		},
		VIDEO_BUCKETS(R.id.nav_video_buckets) {
			@Override
			/* package */ Fragment page() {
				return new VideoBucketsFragment();
			}
		},
		ABOUT(R.id.nav_about) {
			@Override
			/* package */ Fragment page() {
				return new AboutFragment();
			}
		};

		private final int menuId;

		/* package */ NavigationItem(int menuId) {
			this.menuId = menuId;
		}

		/* package */ static NavigationItem forMenuItem(MenuItem item) {
			return forMenuItemId(item.getItemId());
		}

		/* package */ static NavigationItem forMenuItemId(int id) {
			for (NavigationItem value : values()) {
				if (value.menuId == id)
					return value;
			}
			throw new IllegalArgumentException("unknown menu item");
		}

		/* package */ abstract Fragment page();
	}
}
