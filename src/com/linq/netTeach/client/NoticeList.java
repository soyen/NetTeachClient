package com.linq.netTeach.client;

import com.linq.netTeach.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class NoticeList extends FragmentActivity {

	@Override
	protected Fragment getFragment() {
		NoticeListFragment fragment = new NoticeListFragment();
		Bundle arguments = new Bundle();
		arguments.putString("action"
			, getIntent().getStringExtra("action"));
//		arguments.putString("method"
//				, getIntent().getStringExtra("method"));
		fragment.setArguments(arguments);
		return fragment;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = new MenuInflater(this);
		menuInflater.inflate(R.menu.notice_menu, menu);
		return super.onCreateOptionsMenu(menu);
		
	}

	
}
