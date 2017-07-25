package com.example.sj.library.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class OFDialog extends Dialog{
	public OFDialog(Context context, int theme) {
		super(context, theme);
		Window w=this.getWindow();
		w.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		WindowManager.LayoutParams wl = w.getAttributes();
		wl.gravity=Gravity.CENTER;
        w.setAttributes(wl);
	}
	
	public void show(int dy){
		Window w=this.getWindow();
		WindowManager.LayoutParams wl = w.getAttributes();
		wl.y=dy;
        w.setAttributes(wl);
		super.show();
	}

}