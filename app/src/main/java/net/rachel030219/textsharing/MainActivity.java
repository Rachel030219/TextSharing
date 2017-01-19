package net.rachel030219.textsharing;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		startService(new Intent(this,SendService.class));
		Toast.makeText(this,"Service restarted.",Toast.LENGTH_SHORT).show();
		finish();
    }
}
