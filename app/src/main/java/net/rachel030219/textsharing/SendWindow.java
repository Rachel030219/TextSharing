package net.rachel030219.textsharing;

import android.content.*;
import android.view.*;
import android.widget.*;
import android.widget.LinearLayout.*;

public class SendWindow extends LinearLayout{
	Context context;
	WindowManager manager;
    public SendWindow(final Context context,WindowManager manager) {
        super(context);
		this.context = context;
		this.manager = manager;
        setOrientation(LinearLayout.VERTICAL);// 水平排列

        //设置宽高
        this.setLayoutParams( new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));

        View view = LayoutInflater.from(context).inflate(  
			R.layout.sendwindow, null);
		Button share = (Button)view.findViewById(R.id.sendwindow_share);
		final EditText edit = (EditText)view.findViewById(R.id.sendwindow_edit);
		edit.setFocusable(true);
		edit.requestFocus();
		share.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1){
					Intent intent = new Intent(Intent.ACTION_SEND);
					intent.setType("text/plain");
					intent.putExtra(Intent.EXTRA_TEXT,edit.getText().toString());
					intent.putExtra(Intent.EXTRA_TITLE,"Share by…");
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Intent chooserIntent = Intent.createChooser(intent,"Share by…");
					chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(chooserIntent);
					destroySelf();
				}
			});
		Button back = (Button)view.findViewById(R.id.sendwindow_back);
		back.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1){
					destroySelf();
				}
		});
        this.addView(view);
    }
	public void destroySelf(){
		removeAllViews();
		manager.removeView(this);
	}
}
