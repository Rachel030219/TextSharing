package net.rachel030219.textsharing;

import android.app.*;
import android.content.Context;
import android.graphics.*;
import android.os.*;
import android.view.*;

public class SendActivity extends Activity{
	WindowManager mWindowManager;
	WindowManager.LayoutParams mLayout;
	SendWindow window;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

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
        mWindowManager.addView(window = new SendWindow(this,mWindowManager),mLayout);
	}
}
