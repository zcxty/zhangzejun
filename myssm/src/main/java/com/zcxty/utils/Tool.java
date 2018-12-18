package com.zcxty.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import sun.misc.BASE64Encoder;

public class Tool {

	/**
	 * @param args
	 * @category MD5
	 */
	MessageDigest m;
	BASE64Encoder b;
	String n;
	public String complie(String s){
		try {
			s = s.trim();
			m = MessageDigest.getInstance("MD5");
			b = new BASE64Encoder();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			n = b.encode(m.digest(s.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return n;
	}
	public static void main(String[] args) {
		String str = new Tool().complie("123");
		System.out.println(str);
	}
	//方法一：用JAVA自带的函数
	public static boolean isNumeric(String str){
	 for (int i = str.length();--i>=0;){  
	  if (!Character.isDigit(str.charAt(i))){
	  return false;
	  }
	 }
	 return true;
	}
	//方法二：用正则表达式
	public static boolean isNumeric1(String str){ 
	  Pattern pattern = Pattern.compile("[0-9]*"); 
	  return pattern.matcher(str).matches();  
	} 
	//方法三：用ascii码
	public static boolean isNumeric2(String str){
	  for(int i=str.length();--i>=0;){
	   int chr=str.charAt(i);
	   if(chr<48 || chr>57)
	     return false;
	  }
	  return true;
	}
}
