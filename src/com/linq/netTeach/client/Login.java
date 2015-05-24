package com.linq.netTeach.client;

import java.util.HashMap;
import java.util.Map;


import org.json.JSONObject;

import com.linq.netTeach.R;
import com.linq.netTeach.util.DialogUtil;
import com.linq.netTeach.util.HttpUtil;
import com.linq.netTeach.util.Constant;

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
	// ��������������ı���
	EditText etName, etPass;
	// ���������������ť
	Button bnLogin, bnCancel;
	// ��������еĵ�ѡ��ť
	RadioGroup rgUserType;
	// ��������еı�ѡ�еĵ�ѡ��ť
	RadioButton rbUserType;
	//�û�����
    String userType;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		// ��ȡ�����������༭��
		etName = (EditText) findViewById(R.id.userEditText);
		etPass = (EditText) findViewById(R.id.pwdEditText);
		// ��ȡ�����е�������ť
		bnLogin = (Button) findViewById(R.id.bnLogin);
		bnCancel = (Button) findViewById(R.id.bnCancel);
		// ��ȡ�����еĵ�ѡ��ť
		rgUserType = (RadioGroup)findViewById(R.id.rgUserType);
		// ΪbnCancal��ť�ĵ����¼����¼�������
		bnCancel.setOnClickListener(new HomeListener(this));
		bnLogin.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// ִ������У��
				if (validate())  //��
				{
					// �����¼�ɹ�
					if (loginPro())  //��
					{
						// ����Main Activity
						Intent intent = new Intent(Login.this, NetTeachClientActivity.class);	
						intent.putExtra("userType", userType);
						startActivity(intent);
						// ������Activity
						finish();
					}
					else
					{
						DialogUtil.showDialog(Login.this
							, "�û����ƻ�������������������룡", false);
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
		// ��ȡ�û�������û���������
		String username = etName.getText().toString();
		String pwd = etPass.getText().toString();
		// ��ȡ�û�����        
        switch (rbUserType.getId()) {
		case R.id.rbStudent:
			userType = Constant.STUDENT.toString();
			break;
		case R.id.rbTeacher:
			userType = Constant.TEACHER.toString();	
		    break;
		case R.id.rbAdmin:
			userType = Constant.ADMIN.toString();
			break;
		default:
		    userType = "";
		    break;
		}

		JSONObject jsonObj;
		try
		{
			jsonObj = query(username, pwd, userType);
			// ���userId ����0
			if (jsonObj.getInt("id") > 0)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			DialogUtil.showDialog(this
				, "��������Ӧ�쳣�����Ժ����ԣ�", false);
			e.printStackTrace();
		}

		return false;
	}

	// ���û�������û������������У��
	private boolean validate()
	{
		String username = etName.getText().toString().trim();
		if (username.equals(""))
		{
			DialogUtil.showDialog(this, "�û��˻��Ǳ����", false);
			return false;
		}
		String pwd = etPass.getText().toString().trim();
		if (pwd.equals(""))
		{
			DialogUtil.showDialog(this, "�û������Ǳ����", false);
			return false;
		}
		if(rgUserType.getCheckedRadioButtonId()==-1)
		{
			DialogUtil.showDialog(this, "�û������Ǳ�ѡ�", false);
			return false;
		}
		return true;
	}

	// ���巢������ķ���
	private JSONObject query(String username, String password,String userType)
		throws Exception
	{
		// ʹ��Map��װ�������
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		map.put("userType", userType);
		// ���巢�������URL
		String url = HttpUtil.BASE_URL + "login.jsp";
		// ��������
		return new JSONObject(HttpUtil.postRequest(url, map));
	}
}