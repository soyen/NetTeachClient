package com.linq.netTeach.util;

public enum UserTypeConstant {
STUDENT_USER(1,"学生"),
TEACHER_USER(2,"教师"),
ADMIN_USER(3,"管理员");
// 定义私有变量
private int intValue ;
private String stringValue ;

// 构造函数，枚举类型只能为私有
private UserTypeConstant(int intValue,String stringValue) {
    this.intValue = intValue;
    this.stringValue = stringValue;
}
//构造函数，枚举类型只能为私有
private UserTypeConstant(int intValue) {
 this.intValue = intValue;
}
//构造函数，枚举类型只能为私有
private UserTypeConstant( String stringValue) {
 this . stringValue = stringValue;
}

public String getStringValue() {  
    return stringValue;  
}  

public int getIntValue() {  
    return intValue;  
} 

@Override
public String toString()
{
	return String.valueOf(intValue);
}
public static UserTypeConstant getUserTypeByStringValue(String userTypeName) 
{
	UserTypeConstant userTypeConstant = null;
	if(userTypeName.equals(STUDENT_USER.getStringValue()))
	{
		userTypeConstant = STUDENT_USER;
	}else if(userTypeName.equals(TEACHER_USER.getStringValue()))
	{
		userTypeConstant = TEACHER_USER;
	}else if(userTypeName.equals(ADMIN_USER.getStringValue()))
	{
		userTypeConstant = ADMIN_USER;
	}
	return userTypeConstant;
}
public static UserTypeConstant getUserTypeByIntValue(int userTypeId) 
{
	UserTypeConstant userTypeConstant = null;
	if(userTypeId==STUDENT_USER.getIntValue())
	{
		userTypeConstant = STUDENT_USER;
	}else if(userTypeId==TEACHER_USER.getIntValue())
	{
		userTypeConstant = TEACHER_USER;
	}else if(userTypeId==ADMIN_USER.getIntValue())
	{
		userTypeConstant = ADMIN_USER;
	}
	return userTypeConstant;
}
}
