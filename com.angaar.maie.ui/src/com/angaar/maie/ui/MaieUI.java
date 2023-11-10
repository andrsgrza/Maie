package com.angaar.maie.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.angaar.maie.components.quiz.Quiz;
import com.angaar.maie.handlers.io.QuizHandler;
import com.angaar.maie.handlers.io.QuizLoader;
import com.angaar.maie.handlers.io.QuizReader;
import com.angaar.maie.handlers.io.QuizSaver;
import com.angaar.maie.handlers.io.SerializedQuizLoader;
import com.angaar.maie.handlers.io.TextQuizReader;
import com.angaar.maie.handlers.io.TextQuizSaver;
import com.angaar.maie.service.quizperfromer.GeneralQuizPerformer;
import com.angaar.maie.service.quizperfromer.QuizPerformer;
import com.angaar.maie.service.report.GeneralReport;
import com.angaar.maie.util.io.Prompter;

public class MaieUI {
	private static Prompter prompt = new Prompter(System.in);
	private static QuizReader reader = new TextQuizReader();
	private static QuizPerformer performer = new GeneralQuizPerformer();
	private static QuizHandler handler = new QuizHandler();
	private static final String QUIZ_FOLDER_PATH = "C:\\Users\\andre\\OneDrive\\Documentos\\Quizes";    	
	private static final String REDO_FOLDER_PATH = "C:\\Users\\andre\\OneDrive\\Documentos\\RedoQuizzes\\";
	private static final String REDO_QUESTION = "Select the following oprtions\n"
			+ "\t Enter 'Y' to perform redo quiz with incorrect opptions\n"
			+ "\t Enter 'S' to save redo quiz for later";
	
	public static void main(String[] args) throws IOException {
        File testsDirectory = new File(QUIZ_FOLDER_PATH);
        File redoDirectory = new File(REDO_FOLDER_PATH);				    
    	displayMainOptions(testsDirectory, redoDirectory);
	} 
	
	public static GeneralReport createAndPrintReport(Quiz q) {
		GeneralReport rep = new GeneralReport(q);
		rep.printReport();
		return rep;
	}
	
	public static void displayMainOptions(File testsDirectory, File redoDirectory) {
		String mainOptions = "Select one of the follwing options:\n"
				+ "\t 'P'\tPerform Quiz\n"
				+ "\t 'R'\tPerform Saved Redo Quiz";
		String answer = prompt.promptString(mainOptions);
		if(answer.toUpperCase().equals("P")) {
			performListedFiles(testsDirectory, redoDirectory,false);
		}else if(answer.toUpperCase().equals("R")) {
			performListedFiles(redoDirectory, redoDirectory,true);
			//loadAndPerform(redoFile);
		}
	}
	
	public static void performListedFiles(File testsDirectory, File redoDirectory, boolean serial) {
		List<File> selectedFiles = listFiles(testsDirectory, serial);
		if(!selectedFiles.isEmpty()) {    	
    		selectedFiles.stream().forEach(f -> System.out.println(f.getName()));
    		selectedFiles.stream().forEach(f -> {
				
				Quiz performedQuiz = null;
				try {
					if(serial) {
						performedQuiz = loadPerformAndReport(f,serial);
					}else {
						performedQuiz = readPerformAndReport(f);
					}					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(performedQuiz != null) {
					redoOrSave(performedQuiz, redoDirectory);
				}			
				
			});
    	}else {
    		System.out.println("No files selected, exit finishing program");
    	}
	}
	public static Quiz loadPerformAndReport(File redoFile, boolean serial) {
		
		QuizLoader loader = new SerializedQuizLoader(redoFile);
		Quiz quiz = loader.loadQuiz(redoFile);
		performer.performQuiz(quiz);
		createAndPrintReport(quiz);
		return quiz;
	}
	
	public static List<File> listFiles(File directory, boolean serial) {
		String suffix = serial?".ser":".txt";
		List<File> selectedFiles=new ArrayList<File>();
		File[] files = directory.listFiles((dir, name) -> name.endsWith(suffix));	
		selectedFiles = prompt.bigPrompt("Enter, separated by comas, the numbers of selected files", files);
		return selectedFiles;
	}
	public static Quiz readPerformAndReport(File f) throws IOException, CloneNotSupportedException {
		Quiz q = reader.readQuiz(f);
		performer.performQuiz(q);
		createAndPrintReport(q);
		return q;
	}
	public static void redoOrSave(Quiz performedQuiz, File redoDirectory) {
		String redoOrSave = prompt.promptString(REDO_QUESTION).toUpperCase();
		if(redoOrSave.equals("Y")) {
			performedQuiz = handler.createRedoQuiz(performedQuiz);
			performer.performQuiz(performedQuiz);						
		}else if(redoOrSave.equals("S")) {
			performedQuiz = handler.createRedoQuiz(performedQuiz);			
			QuizSaver saver = new TextQuizSaver(performedQuiz,redoDirectory);
			saver.saveQuiz();
		}
	}
}
