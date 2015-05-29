package com.linq.netTeach.client;

import com.linq.netTeach.R;
import com.linq.netTeach.util.UserTypeConstant;

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

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}
	@Override
	public void onItemSelected(Integer id , Bundle bundle)
	{
		Intent intent = null;
		NetTeachClientApplication netTeachClientApplication =(NetTeachClientApplication)getApplication();
		int userTypeInt = netTeachClientApplication.getUserType();
		// 获取用户类型      
		UserTypeConstant userTypeConstant = UserTypeConstant.getUserTypeByIntValue(userTypeInt);
        switch (userTypeConstant) {
		case STUDENT_USER:
			
			break;
		case TEACHER_USER:
			
		    break;
		case ADMIN_USER:
			switch ((int) id)
			{
				// 公告管理
				case 0:
					// 启动NoticeList Activity
					intent = new Intent(this, NoticeList.class);
					// action属性为请求的Servlet地址。
					intent.putExtra("action", "notice.do?method=listNotice");
//					intent.putExtra("method", "listNotice");
					startActivity(intent);
					break;
				// 浏览流拍物品
				case 1:
//					// 启动ViewItem Activity
//					intent = new Intent(this, ViewItem.class);
//					// action属性为请求的Servlet的URL。
//					intent.putExtra("action", "viewFail.jsp");
//					startActivity(intent);
					break;
				// 管理物品种类
				case 2:
//					// 启动ManageKind Activity
//					intent = new Intent(this, ManageKind.class);
//					startActivity(intent);
					break;
				// 管理物品
				case 3:
//					// 启动ManageItem Activity
//					intent = new Intent(this, ManageItem.class);
//					startActivity(intent);
					break;
				// 浏览拍卖物品（选择物品种类）
				case 4:
//					// 启动ChooseKind Activity
//					intent = new Intent(this, ChooseKind.class);
//					startActivity(intent);
					break;
				// 查看自己的竞标
				case 5:
//					// 启动ViewBid Activity
//					intent = new Intent(this, ViewBid.class);
//					startActivity(intent);
					break;
			}
			break;
		default:

		    break;
        }
		
	}
}
