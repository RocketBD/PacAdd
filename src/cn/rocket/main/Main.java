package cn.rocket.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import cn.rocket.exception.EmptyParameterExcption;
import cn.rocket.exception.IncompleteParameterException;
import cn.rocket.exception.WrongAddressException;

public class Main {
	private static void checkArgs(String[] args) {
		for (int i = 1; i < args.length; i++) 
			if(!Util.isAddress(args[i]))
				try {
					throw new WrongAddressException(args[i]);
				} catch (WrongAddressException e) {
					e.printStackTrace();
					System.exit(4);
				}
	}
	
	private static void resolve(String[] args) {
		File pac = new File(System.getenv("temp")+"\\_MEI"+args[0]+"\\template\\pac");
		Util.isWritable(pac);
		checkArgs(args);
		StringBuffer out = new StringBuffer();
		FileInputStream fis = null;
		int c;
		try {
			fis = new FileInputStream(pac);
			while((int)(c=fis.read())!=-1)
				out.append((char)c);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int frontIndex = out.lastIndexOf("1", out.indexOf("}", 733));
		for (int i = 1; i < args.length; i++) 
			out.insert(frontIndex+1, ",\n  \""+args[i]+"\": 1");
		try {
			PrintWriter pw = new PrintWriter(pac);
			pw.println(out.toString());
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Pac added successfully.");
	}
	
	private static void printGuide() {
		System.out.println("java -jar PacAdd.jar dest|? address [address] ...");
		System.out.println("?\tPrint this user guide.");
		System.out.println("dest\tThe folder which contains the pac of Accesser.\n"
				+ "\tFor example, if the pac is in %tmp%\\_MEI19202, you should type 19202 here.");
		System.out.println("address\tYou can type addresses you want to enter with Accesser here,"
				+ " by seprating them by \" \".\n"
				+ "\tPlease type down the address directly like youtube.com .");
		System.exit(0);
	}
	
	public static void main(String[] args) {
		if(args.length==0)
			try {
				throw new EmptyParameterExcption();
			} catch (EmptyParameterExcption e) {
				e.printStackTrace();
				System.exit(1);
			}
		if(args[0].contentEquals("?")||args[0].contentEquals("-?")||args[0].contentEquals("/?"))
			printGuide();
		if(args.length==1)
			try {
				throw new IncompleteParameterException();
			} catch (IncompleteParameterException e) {
				e.printStackTrace();
				System.exit(2);
			}
		resolve(args);
	}
}
