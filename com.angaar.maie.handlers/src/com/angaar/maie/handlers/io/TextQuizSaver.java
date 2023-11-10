package com.angaar.maie.handlers.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.angaar.maie.components.quiz.Quiz;

public class TextQuizSaver implements QuizSaver{
	File fileToSave;
	Quiz quiz;
	public TextQuizSaver(Quiz quiz, File saveDirectory) {
		this.quiz = quiz;
		this.fileToSave = new File(saveDirectory.getAbsolutePath()+"\\"+quiz.getName()+".ser");;
	}

	@Override
	public void saveQuiz() {
		try (FileOutputStream fileOutputStream = new FileOutputStream(fileToSave);
	             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
	            objectOutputStream.writeObject(quiz);
	            System.out.println("Quiz saved to " + fileToSave);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}

}
