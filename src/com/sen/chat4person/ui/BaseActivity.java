package com.sen.chat4person.ui;

import com.sen.chat4person.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;

public class BaseActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//返回键 在base中绑定退出确认的弹窗
		if(KeyEvent.KEYCODE_BACK == keyCode){
			showDialog();
			return true;
		}else{
			return super.onKeyDown(keyCode, event);
		}
	}

	private void showDialog() {
		AlertDialog.Builder dg = new AlertDialog.Builder(this);
		dg.setTitle(R.string.quit);
		dg.setMessage(R.string.quitConfirm);
		dg.setPositiveButton(R.string.onfirm, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
	}
}
