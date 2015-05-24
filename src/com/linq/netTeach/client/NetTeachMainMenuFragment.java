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

	// ��д�÷������÷������ص�View����ΪFragment��ʾ�����
	@Override
	public View onCreateView(LayoutInflater inflater
		, ViewGroup container, Bundle savedInstanceState)
	{
		// ����/res/layout/Ŀ¼�µ�main_menu.xml�����ļ�
		View rootView = inflater.inflate(R.layout.main_menu,container, false);
		menuList = (ListView) rootView.findViewById(R.id.menu_list);
		NetTeachClientActivity netTeachClientActivity = (NetTeachClientActivity)getActivity();		
		String userType = netTeachClientActivity.userType;
		int menuId = -1;
		// ��ȡ�û�����        
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
		// ΪListView���б���ĵ����¼����¼�������
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
	
	// ����Fragment����ӡ���ʾ��Activityʱ���ص��÷���
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		// ���Activityû��ʵ��Callbacks�ӿڣ��׳��쳣
		if (!(activity instanceof Callbacks))
		{
			throw new IllegalStateException(
				"NetTeachListFragment���ڵ�Activity����ʵ��Callbacks�ӿ�!");
		}
		// �Ѹ�Activity����Callbacks����
		mCallbacks = (Callbacks) activity;
	}
	// ����Fragment����������Activity�б�ɾ��ʱ�ص��÷���
	@Override
	public void onDetach()
	{
		super.onDetach();
		// ��mCallbacks��Ϊnull��
		mCallbacks = null;
	}

	public void setActivateOnItemClick(boolean activateOnItemClick)
	{
		menuList.setChoiceMode(activateOnItemClick 
			? ListView.CHOICE_MODE_SINGLE
			: ListView.CHOICE_MODE_NONE);
	}
}