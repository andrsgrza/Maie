package com.angaar.maie.handlers.io;

import java.io.File;
import java.io.IOException;

import com.angaar.maie.components.quiz.Quiz;

public interface QuizReader {	
	public Quiz readQuiz(File inputFile) throws IOException, CloneNotSupportedException;
}
