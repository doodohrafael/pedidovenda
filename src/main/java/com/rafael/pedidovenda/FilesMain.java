package com.rafael.pedidovenda;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FilesMain {

	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("C:/Users/Pinkman/Desktop/textfiles.com_100_captmidn.txt");
		Scanner scanner = new Scanner(file);
		
		while(scanner.hasNextLine()) {
			System.err.println(scanner.nextLine());
		}

	}

}
