package com.drivemode.media.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author KeithYokoma
 */
@SuppressWarnings("unused") // public API
public class SortOrder {
	public static final SortOrder UNSPECIFIED = new SortOrder("", Order.NO_ORDER);
	private final String key;
	private final Order order;

	public SortOrder(@NonNull String key, @NonNull Order order) {
		this.key = key;
		this.order = order;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof SortOrder))
			return false;
		SortOrder sortOrder = (SortOrder) o;
		return key.equals(sortOrder.key) && order == sortOrder.order;

	}

	@Override
	public int hashCode() {
		int result = key.hashCode();
		result = 31 * result + order.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "SortOrder{" +
				"key='" + key + '\'' +
				", order=" + order +
				'}';
	}

	public @Nullable String toSql() {
		if (UNSPECIFIED.equals(this))
			return null;
		return key + " " + order.sql;
	}

	public enum Order {
		ASCENDING("ASC"), DESCENDING("DESC"), NO_ORDER("");

		private final String sql;

		/* package */ Order(String sql) {
			this.sql = sql;
		}
	}
}
