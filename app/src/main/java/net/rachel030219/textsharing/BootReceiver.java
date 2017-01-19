package net.rachel030219.textsharing;

import android.content.*;

public class BootReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent){
		if(intent.getAction() == Intent.ACTION_BOOT_COMPLETED){
			context.startService(new Intent(context,SendService.class));
		}
	}
	
}
