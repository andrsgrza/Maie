package com.angaar.maie.handlers.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.angaar.maie.components.quiz.Quiz;

public class SerializedQuizLoader implements QuizLoader{
	File loadFile;
	
	public SerializedQuizLoader(File loadFile) {
		this.loadFile = loadFile;
	}
	
	@Override
	public Quiz loadQuiz(File f) {
		try (FileInputStream fileInputStream = new FileInputStream(loadFile);
	             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
	            Quiz quiz = (Quiz) objectInputStream.readObject();
	            System.out.println("Quiz loaded from " + loadFile);
	            return quiz;
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	            return null;
	        }
		
	}

}
