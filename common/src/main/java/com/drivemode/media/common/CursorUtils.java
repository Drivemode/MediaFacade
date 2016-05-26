package com.drivemode.media.common;

import android.database.Cursor;

/**
 * @author KeithYokoma
 */
public final class CursorUtils {
	private CursorUtils() {
		throw new AssertionError();
	}

	public static void close(Cursor cursor) {
		if (cursor == null)
			return;
		cursor.close();
	}
}
