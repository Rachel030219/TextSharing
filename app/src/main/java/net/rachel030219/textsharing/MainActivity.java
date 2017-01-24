package net.rachel030219.textsharing;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends Activity {
    public static final int REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		startService(new Intent(this,SendService.class));
		Toast.makeText(this,"Service restarted.",Toast.LENGTH_SHORT).show();
		finish();
    }
}
