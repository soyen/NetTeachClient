package com.linq.netTeach.util;

public enum Constant {
STUDENT(1),TEACHER(2),ADMIN(3);
// 定义私有变量
private int nCode ;

// 构造函数，枚举类型只能为私有
private Constant( int _nCode) {
    this . nCode = _nCode;
}
@Override
public String toString() {
    return String.valueOf ( this . nCode );
}
public int getnCode() {
	return nCode;
}

}
