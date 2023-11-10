package com.angaar.maie.handlers.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.angaar.maie.components.category.Category;
import com.angaar.maie.components.category.GeneralCategory;
import com.angaar.maie.components.question.GeneralQuestion;
import com.angaar.maie.components.question.Question;
import com.angaar.maie.components.quiz.GeneralQuiz;
import com.angaar.maie.components.quiz.Quiz;
import com.angaar.maie.util.io.TextAnalyzer;

public class TextQuizReader implements QuizReader{	
	public TextAnalyzer ta = new TextAnalyzer();
	public Quiz  quiz = new GeneralQuiz();
	
	@Override
	public Quiz readQuiz(File inputFile) throws IOException, CloneNotSupportedException {
		try (FileReader fileReader = new FileReader(inputFile.getAbsoluteFile()); BufferedReader bufferedReader = new BufferedReader(fileReader)){
			String quizTitle = bufferedReader.readLine();
			quiz.setName(quizTitle);
			String line;
			while( (line = bufferedReader.readLine()) != null) {
				if(line.isEmpty() | line.isBlank()) {
					continue;
				}

				if(isCategory(line)) {
					while(line != null && isCategory(line)) {
						line = populateCategory(bufferedReader,line);
					}
					
				}
				
			}
		}
		return quiz;
	}
	
	private boolean isCategory(String line) {
		return TextAnalyzer.isValidRomanNumeral(line);
	}
	private boolean isQuestion(String line) {
		return TextAnalyzer.isQuestion(line);
	}
	private boolean isAnswer(String line) {
		return TextAnalyzer.isAnswer(line);
	}
	
	public String populateCategory(BufferedReader reader, String title) throws IOException, CloneNotSupportedException {
		String line;		
		
		Question q = new GeneralQuestion();
		Category c = new GeneralCategory(title);
		
		while((line = reader.readLine()) != null && !isCategory(line) ) {
			if(line.isEmpty() || line.isBlank()) {
				continue;
			}
			if(isQuestion(line)) {
				if(q.hasAnswer()) {
					c.addQuestion(q);
					q.setNull();
					q.appendQuestion(line);
				}else {
					q.appendQuestion(line);
				}
				
			}else if(isAnswer(line)) {
				q.appendAnswer(line);
			}
		}
		c.addQuestion(q);
		quiz.addCategory(c);
		return line;
	}

}
