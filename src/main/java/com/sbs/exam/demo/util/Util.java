package com.sbs.exam.demo.util;

public class Util {

	public static boolean isParamEmpty(Object obj) {

		if (obj == null) {
			return true;
		}
		
		if (obj instanceof String == false) {
			// instanceof 객체의 타입을 확인하는 연산자
			return true;
		}
		
		String str = (String) obj;
		
		return str.trim().length() == 0;
	};
}
