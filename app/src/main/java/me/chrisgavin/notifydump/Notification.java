package me.chrisgavin.notifydump;

import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;


public class Notification {
	public final String key;
	public final String packageName;
	public final Map<String, Object> extras;

	private static final Set<Class> SERIALIZABLE_EXTRA_TYPES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(
		String.class,
		Integer.class,
		Boolean.class,
		null
	)));

	public Notification(StatusBarNotification statusBarNotification) {
		this.key = statusBarNotification.getKey();
		this.packageName = statusBarNotification.getPackageName();
		this.extras = new LinkedHashMap<>();
		for (String key : statusBarNotification.getNotification().extras.keySet()) {
			Object value = statusBarNotification.getNotification().extras.get(key);
			Class<?> type = value != null ? value.getClass() : null;
			if (SERIALIZABLE_EXTRA_TYPES.contains(type)) {
				this.extras.put(key, value);
			}
			else {
				Log.i(Notification.class.getSimpleName(), "Not serializing extra \"" + key + "\" on notification \"" + this.key + "\" because " + type + " is not JSON serializable.");
			}
		}
	}

	@Override
	public boolean equals(Object other) {
		return (other instanceof Notification) && (key.equals(((Notification) other).key));
	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}
}
