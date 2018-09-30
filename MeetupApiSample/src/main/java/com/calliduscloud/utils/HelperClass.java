package com.calliduscloud.utils;

import java.util.Scanner;

public class HelperClass {

	static Scanner sc = new Scanner(System.in);

	public static int readInt() {
		while (sc.hasNextInt() == false) {
			System.out.println("Invalid value, try again");
			sc.nextLine();
		}
		int num = sc.nextInt();
		sc.nextLine();
		return num;
	}
	
}
