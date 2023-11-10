package com.angaar.maie.util.io;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Prompter {
	private InputStream inputStream=System.in;
	private Scanner sc = new Scanner(inputStream);
	
	public Prompter(InputStream inputStream) {
		this.inputStream=inputStream;
	}
	
	public String promptString(String message) {
		System.out.println(message);
		String returnString = sc.nextLine();
		return returnString;
	}
	
	public List<File> promptList(String message, File[] files) {
		List<File> selectedFiles = new ArrayList<>();
        for(File file:files) {
        	String userInput = promptString(message + file.getName() + "? (y/n)");        	    
            if (userInput.equalsIgnoreCase("y")) {
            	selectedFiles.add(file);
            }
        }
		return selectedFiles;		
	}
	public List<File> bigPrompt(String message, File[] files) {
		//System.out.println("Select the files you want to include. \nSeparate the numbers with spaces");
		List<File> selectedFiles = new ArrayList<>();
		Map<Integer, File> fileMap = new HashMap<Integer, File>();
		for(int x=0; x<files.length;x++) {
			fileMap.put(x+1, files[x]);
		}
		fileMap.entrySet().forEach(ent -> System.out.println(ent.getKey()+"\t"+ent.getValue().getName()));
		String userInput = promptString(message);
		String [] inputs=userInput.split(" ");
		//Validator.validateNumberList(inputs);
		for(String input:inputs) {
			Integer intInput= Integer.parseInt(input);
			selectedFiles.add(fileMap.get(intInput));
			
		}		
		return selectedFiles;
	}
	
	public List<Object> promptList(String message, List<Object> categories) {
		List<Object> selectedCategories = new ArrayList<>();
        for(Object category:categories) {
        	String userInput = promptString(message + /*category.getName() +*/ "? (y/n)");        	    
            if (userInput.equalsIgnoreCase("y")) {
            	selectedCategories.add(category);
            }
        }
		return selectedCategories;		
	}
}