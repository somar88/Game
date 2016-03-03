package com.abrahms.game.utilities;

import java.util.Scanner;

public class IOManager {

	public static Scanner scanner = new Scanner(System.in);
	
	public static String getInput(String message){
		System.out.println(message);
		return scanner.nextLine();		
	}
	
	
}
