package com.linq.netTeach.client;

import java.util.HashMap;
import java.util.Map;


import org.json.JSONObject;

import com.linq.netTeach.R;
import com.linq.netTeach.util.DialogUtil;
import com.linq.netTeach.util.HttpUtil;
import com.linq.netTeach.util.UserTypeConstant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
public class Login extends Activity
{
	// 定义界面中两个文本框
	EditText etName, etPass;
	// 定义界面中两个按钮
	Button bnLogin, bnCancel;
	// 定义界面中的单选按钮
	RadioGroup rgUserType;
	// 定义界面中的被选中的单选按钮
	RadioButton rbUserType;
	//用户类型
    String userType;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		// 获取界面中两个编辑框
		etName = (EditText) findViewById(R.id.userEditText);
		etPass = (EditText) findViewById(R.id.pwdEditText);
		// 获取界面中的两个按钮
		bnLogin = (Button) findViewById(R.id.bnLogin);
		bnCancel = (Button) findViewById(R.id.bnCancel);
		// 获取界面中的单选按钮
		rgUserType = (RadioGroup)findViewById(R.id.rgUserType);
		// 为bnCancal按钮的单击事件绑定事件监听器
		bnCancel.setOnClickListener(new HomeListener(this));
		bnLogin.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 执行输入校验
				if (validate())  //①
				{
					// 如果登录成功
					if (loginPro())  //②
					{
						// 启动Main Activity
						Intent intent = new Intent(Login.this, NetTeachClientActivity.class);	
						intent.putExtra("userType", userType);
						startActivity(intent);
						// 结束该Activity
						finish();
					}
					else
					{
						DialogUtil.showDialog(Login.this
							, "用户名称或者密码错误，请重新输入！", false);
					}
				}
			}
		});
		rgUserType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				rbUserType = (RadioButton)findViewById(checkedId);				
			}
		});
	}

	private boolean loginPro()
	{
		// 获取用户输入的用户名、密码
		String username = etName.getText().toString();
		String pwd = etPass.getText().toString();		
		// 获取用户类型      
		UserTypeConstant userTypeConstant = UserTypeConstant.getUserTypeByStringValue(rbUserType.getText().toString());
        switch (userTypeConstant) {
		case STUDENT_USER:
			userType = UserTypeConstant.STUDENT_USER.toString();
			break;
		case TEACHER_USER:
			userType = UserTypeConstant.TEACHER_USER.toString();
		    break;
		case ADMIN_USER:
			userType = UserTypeConstant.ADMIN_USER.toString();
			break;
		default:
		    userType = "";
		    break;
        }

		JSONObject jsonObj;
		try
		{
			jsonObj = query(username, pwd, userType);
			// 如果userId 大于0
			if (jsonObj.getInt("id") > 0)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(this
				, "服务器响应异常，请稍后再试！", false);
			e.printStackTrace();
		}

		return false;
	}

	// 对用户输入的用户名、密码进行校验
	private boolean validate()
	{
		String username = etName.getText().toString().trim();
		if (username.equals(""))
		{
			DialogUtil.showDialog(this, "用户账户是必填项！", false);
			return false;
		}
		String pwd = etPass.getText().toString().trim();
		if (pwd.equals(""))
		{
			DialogUtil.showDialog(this, "用户口令是必填项！", false);
			return false;
		}
		if(rgUserType.getCheckedRadioButtonId()==-1)
		{
			DialogUtil.showDialog(this, "用户类型是必选项！", false);
			return false;
		}
		return true;
	}

	// 定义发送请求的方法
	private JSONObject query(String username, String password,String userType)
		throws Exception
	{
		// 使用Map封装请求参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		map.put("userType", userType);
		// 定义发送请求的URL
		String url = HttpUtil.BASE_URL + "login.jsp";
		// 发送请求
		return new JSONObject(HttpUtil.postRequest(url, map));
	}
}