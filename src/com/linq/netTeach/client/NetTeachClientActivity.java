package com.linq.netTeach.client;

import com.linq.netTeach.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Description:
 * <br/>website: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class NetTeachClientActivity extends Activity
	implements Callbacks
{
    String userType;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		userType = intent.getStringExtra("userType");
		setContentView(R.layout.activity_main);

	}
	@Override
	public void onItemSelected(Integer id , Bundle bundle)
	{

	}
}
