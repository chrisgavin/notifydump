package me.chrisgavin.notifydump;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import java.util.LinkedHashSet;
import java.util.Set;

public class NotifyDumpService extends NotificationListenerService {
	private static NotifyDumpService instance = null;

	@Override
	public void onListenerConnected() {
		instance = this;
	}

	public static NotifyDumpService getInstance() {
		return instance;
	}

	public Set<Notification> getNotifications() {
		Set<Notification> result = new LinkedHashSet<>();
		for (StatusBarNotification statusBarNotification : getActiveNotifications()) {
			result.add(new Notification(statusBarNotification));
		}
		return result;
	}
}
