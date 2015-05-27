package com.linq.netTeach.client;

import android.app.Fragment;
import android.os.Bundle;

public class NoticeList extends FragmentActivity {

	@Override
	protected Fragment getFragment() {
		NoticeListFragment fragment = new NoticeListFragment();
		Bundle arguments = new Bundle();
		arguments.putString("action"
			, getIntent().getStringExtra("action"));
		fragment.setArguments(arguments);
		return fragment;
	}

}
