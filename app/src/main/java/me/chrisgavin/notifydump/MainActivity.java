package me.chrisgavin.notifydump;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent serviceIntent = new Intent(this, NotifyDumpService.class);
		startService(serviceIntent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (!NotificationManagerCompat.getEnabledListenerPackages(this).contains(getPackageName())) {
			new AlertDialog.Builder(this)
				.setTitle(R.string.permissions_request_title)
				.setMessage(R.string.permissions_request_text)
				.setPositiveButton(R.string.permissions_request_ok, (dialogInterface, i) -> startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)))
				.setOnCancelListener((dialogInterface) -> finish())
				.show();
		}
	}
}
