package com.linq.netTeach.util;

public enum Constant {
STUDENT(1),TEACHER(2),ADMIN(3);
// ����˽�б���
private int nCode ;

// ���캯����ö������ֻ��Ϊ˽��
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
