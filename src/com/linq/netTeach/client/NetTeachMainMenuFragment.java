package com.linq.netTeach.client;

import com.linq.netTeach.R;

import android.app.Activity;
import android.app.Fragment;



import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Description:
 * <br/>site: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class NetTeachMainMenuFragment extends Fragment
{	
	ListView menuList;
	String[] menu; 
	private Callbacks mCallbacks;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	}

	// 重写该方法，该方法返回的View将作为Fragment显示的组件
	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle savedInstanceState)
	{
		// 加载/res/layout/目录下的main_menu.xml布局文件
		View rootView = inflater.inflate(R.layout.main_menu,container, false);
		menuList = (ListView) rootView.findViewById(R.id.menu_list);
		NetTeachClientActivity netTeachClientActivity = (NetTeachClientActivity)getActivity();		
		String userType = netTeachClientActivity.userType;
		int menuId = -1;
		// 获取用户类型        
        switch (Integer.parseInt(userType)) {
		case 1:
			menuId = R.array.student_menu;
			break;
		case 2:
			menuId = R.array.teacher_menu;
		    break;
		case 3:
			menuId = R.array.admin_menu;
			break;
		default:
		    menuId = -1;
		    break;
		}
        menu = getResources().getStringArray(menuId);		
		ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, menu);
		menuList.setAdapter(menuAdapter);
		// 为ListView的列表项的单击事件绑定事件监听器
		menuList.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				int position, long id)
			{
				mCallbacks.onItemSelected(position , null);
			}
		});
		return rootView;
	}	
	
	// 当该Fragment被添加、显示到Activity时，回调该方法
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		// 如果Activity没有实现Callbacks接口，抛出异常
		if (!(activity instanceof Callbacks))
		{
			throw new IllegalStateException(
				"NetTeachListFragment所在的Activity必须实现Callbacks接口!");
		}
		// 把该Activity当成Callbacks对象
		mCallbacks = (Callbacks) activity;
	}
	// 当该Fragment从它所属的Activity中被删除时回调该方法
	@Override
	public void onDetach()
	{
		super.onDetach();
		// 将mCallbacks赋为null。
		mCallbacks = null;
	}

	public void setActivateOnItemClick(boolean activateOnItemClick)
	{
		menuList.setChoiceMode(activateOnItemClick 
			? ListView.CHOICE_MODE_SINGLE
			: ListView.CHOICE_MODE_NONE);
	}
}