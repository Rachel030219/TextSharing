package net.rachel030219.textsharing;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;

public class SendService extends Service
{

	@Override
	public IBinder onBind(Intent p1)
	{
		return null;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		Intent intent = new Intent(this,SendActivity.class); 
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0); 
		//创建一个通知
		Notification.Builder builder = new Notification.Builder(this);
		builder.setContentTitle("TextSharing is running…");
		builder.setContentText("Click to start sharing!");
		builder.setSmallIcon(R.drawable.ic_notification);
		builder.setContentIntent(pendingIntent);
		builder.setOngoing(true);
		builder.setAutoCancel(false);
		builder.setPriority(Notification.PRIORITY_MIN);
		Notification notification = builder.getNotification();
		//用NotificationManager的notify方法通知用户生成标题栏消息通知 
		NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); 
		nManager.notify(1081, notification);
	}
	
}
