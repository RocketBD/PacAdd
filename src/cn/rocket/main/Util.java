package cn.rocket.main;

import java.io.File;
import java.io.IOException;

public class Util {
	public static boolean isNumber(String str) {
		for (int i = 0; i < str.length(); i++) 
			if(str.charAt(i)<'0'||str.charAt(i)>'9')
				return false;
		return true;
	}
	
	private static boolean isIP(String address) {
		String[] ss = address.split("\\.");
		if(ss.length==0)
			return false;
		for (int i = 0; i < ss.length; i++) 
			if(new Integer(ss[i]).intValue()>255)
				return false;
		return true;
	}
	
	public static boolean containAlpha(String str) {
		for(int i = 0;i < str.length();i++)
			if(Character.isAlphabetic(str.charAt(i)))
				return true;
		return false;
	}
	
	public static boolean isDomain(String address) {
		String[] ss = address.split("\\.");
		if(ss.length==0)
			return false;
		for (int i = 0; i < ss.length; i++) {
			if(!containAlpha(ss[i]))
				return false;
		}
		return true;
	}
	
	public static int countSpecifiledString(String content,String target) {
		int count = 0;
		int fromIndex = 0;
        while(true){
            int index = content.indexOf(target, fromIndex);
            if(-1 != index){
                fromIndex = index + 1;
                count++;
            } else {
                break;
            }
        }
		return count;
	}
	
	public static String deleteString(String content,String target) {
		StringBuffer sb = new StringBuffer();
		String[] ss = content.split(target);
		for (int i = 0; i < ss.length; i++) {
			sb.append(ss[i]);
		}
		return sb.toString();
	}
	
	protected static boolean isAddress(String str) {
		if(!str.contains("."))
			return false;
		if(isDomain(str))
			return true;
		if(isNumber(deleteString(str, "."))&&countSpecifiledString(str, ".")==3&&isIP(str))
			return true;
		return false;
	}
	
	public static void isWritable(File f) {
		if(!f.exists())
			try {
				throw new IOException("Specifiled file is NOT exist!");
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(5);
			}
		if(!f.isFile())
			try {
				throw new IOException("Specifiled file is a directory!");
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(5);
			}
		if(!f.canWrite())
			try {
				throw new IOException("Specifiled file can NOT be written!"
						+ " You may try executing the program again with a administrator identity.");
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(5);
			}
		
	}
}
