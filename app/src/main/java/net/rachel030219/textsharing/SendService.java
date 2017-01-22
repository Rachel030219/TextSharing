package net.rachel030219.textsharing;

import android.app.*;
import android.content.*;
import android.graphics.PixelFormat;
import android.os.*;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.*;

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
                    WindowManager mWindowManager;
                    WindowManager.LayoutParams mLayout;
                    mWindowManager = (WindowManager) getApplicationContext()
                        .getSystemService(Context.WINDOW_SERVICE);
                    mLayout = new WindowManager.LayoutParams();
                    mLayout.type = WindowManager.LayoutParams.TYPE_PHONE;

                    // 设置窗体焦点及触摸：
                    // FLAG_NOT_FOCUSABLE(不能获得按键输入焦点)
                    mLayout.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;

                    // 设置显示的模式
                    mLayout.format = PixelFormat.RGBA_8888;

                    // 设置对齐的方法
                    mLayout.gravity = Gravity.CENTER;

                    // 设置窗体宽度和高度
                    mLayout.width = WindowManager.LayoutParams.MATCH_PARENT;
                    mLayout.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    mWindowManager.addView(new SendWindow(this,mWindowManager),mLayout);
                    break;
            }
        }
        return START_NOT_STICKY;
    }
}
