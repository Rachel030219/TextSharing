package net.rachel030219.textsharing;

import android.Manifest;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.app.Service;
import android.app.PendingIntent;
import android.app.Notification;
import android.provider.Settings;
import android.view.Gravity;
import android.view.WindowManager;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.widget.Toast;

public class SendService extends Service
{
    public static final int REQUEST_WINDOW = 1;

	@Override
	public IBinder onBind(Intent p1)
	{
		return null;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		PendingIntent pendingIntent = PendingIntent.getService(this, REQUEST_WINDOW, new Intent(this,SendService.class).putExtra("request",REQUEST_WINDOW), PendingIntent.FLAG_UPDATE_CURRENT);
		Notification.Builder builder = new Notification.Builder(this);
		builder.setContentTitle("TextSharing is running…");
		builder.setContentText("Click to start sharing!");
		builder.setSmallIcon(R.drawable.ic_notification);
		builder.setContentIntent(pendingIntent);
		builder.setOngoing(true);
		builder.setAutoCancel(false);
		if (Build.VERSION.SDK_INT >= 16)
			builder.setPriority(Notification.PRIORITY_MIN);
		Notification notification = builder.getNotification();
        startForeground(1081,notification);
	}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.hasExtra("request")) {
            switch (intent.getIntExtra("request", 0)) {
                case REQUEST_WINDOW:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                        Toast.makeText(this,R.string.grant_permission_hint,Toast.LENGTH_LONG).show();
                        Intent permissionIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:"+getPackageName())).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(permissionIntent);
                    } else {
                        WindowManager mWindowManager;
                        WindowManager.LayoutParams mLayout;
                        mWindowManager = (WindowManager) getApplicationContext()
                                .getSystemService(Context.WINDOW_SERVICE);
                        mLayout = new WindowManager.LayoutParams();
                        mLayout.type = WindowManager.LayoutParams.TYPE_PHONE;

                        mLayout.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;

                        mLayout.format = PixelFormat.RGBA_8888;

                        mLayout.gravity = Gravity.CENTER;

                        mLayout.width = WindowManager.LayoutParams.MATCH_PARENT;
                        mLayout.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        mWindowManager.addView(new SendWindow(this,mWindowManager),mLayout);
                        break;
                    }
            }
        }
        return START_NOT_STICKY;
    }
}
