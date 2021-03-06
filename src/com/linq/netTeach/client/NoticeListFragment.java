package com.linq.netTeach.client;


import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.linq.netTeach.R;
import com.linq.netTeach.util.DialogUtil;
import com.linq.netTeach.util.HttpUtil;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class NoticeListFragment extends Fragment {

	Button bnHome;
	ListView noticeList;
	TextView viewTitle;
	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.notice_list, container , false);
		// 获取界面上的返回按钮
		bnHome = (Button) rootView.findViewById(R.id.bn_home);
		noticeList = (ListView) rootView.findViewById(R.id.noticeList);
		viewTitle = (TextView) rootView.findViewById(R.id.view_titile);
		// 为返回按钮的单击事件绑定事件监听器
		bnHome.setOnClickListener(new HomeListener(getActivity()));
		String action = getArguments().getString("action");
		String method = getArguments().getString("method");

		// 定义发送请求的URL
		String url = HttpUtil.BASE_URL + action;
		try
		{
			// 向指定URL发送请求，并把服务器响应转换成JSONArray对象
			JSONArray jsonArray = new JSONArray(HttpUtil
				.getRequest(url));  //①
			// 将JSONArray包装成Adapter
			JSONArrayAdapter adapter = new JSONArrayAdapter(getActivity()
				, jsonArray, "title", true);  //②
			noticeList.setAdapter(adapter);
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(getActivity(), "服务器响应异常，请稍后再试！", false);
			e.printStackTrace();
		}
		noticeList.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
			{
				// 查看指定公告的详细情况。
				viewNoticeDetail(position);  //③
			}
		});
		return rootView;
	}
	
	private void viewNoticeDetail(int position)
	{
		// 加载detail.xml界面布局代表的视图
		View detailView = getActivity().getLayoutInflater()
			.inflate(R.layout.notice, null);
		// 获取detail.xml界面布局中的文本框
		TextView noticeTitle = (TextView) detailView
			.findViewById(R.id.noticeTitle);
		TextView noticeContent = (TextView) detailView
			.findViewById(R.id.noticeContent);
		TextView noticeCreateTime = (TextView) detailView
			.findViewById(R.id.noticeCreateTime);

		// 获取被单击的列表项
		JSONObject jsonObj = (JSONObject) noticeList.getAdapter().getItem(position);
		try
		{
			// 通过文本框显示物品详情
			noticeTitle.setText(jsonObj.getString("title"));
			noticeContent.setText(jsonObj.getString("content"));
			noticeCreateTime.setText(jsonObj.getString("createTime"));
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		DialogUtil.showDialog(getActivity(), detailView);
	}
}
