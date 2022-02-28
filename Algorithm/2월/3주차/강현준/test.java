package com.ssafy.study;

import java.io.OutputStream;

public class test {

	public static void main(String[] args) {
		parent a = new parent();
		parent a_b = new child();
		
		System.out.println(a instanceof parent);
		System.out.println(a instanceof child);
		System.out.println(a_b instanceof parent);
		System.out.println(a_b instanceof child);
		
		String s = "abc";
		char[] arr = {'a', 'b', 'c'};
		System.out.println(s.contains("zz"));
		
		OutputStream
		InputStream
	}
	
	
	static class parent{
		
	}
	static class child extends parent{
		
	}
	
}
