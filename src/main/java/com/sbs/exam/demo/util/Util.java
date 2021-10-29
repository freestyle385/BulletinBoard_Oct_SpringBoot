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
	}

	public static String f(String format, Object... args) {
		// 개수가 명확하지 않은 인자를 넘겨줄 때에는 object...
		return String.format(format, args);
	}
}
