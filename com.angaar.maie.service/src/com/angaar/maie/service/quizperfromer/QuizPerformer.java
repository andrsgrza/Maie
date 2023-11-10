package com.angaar.maie.service.quizperfromer;

import com.angaar.maie.components.quiz.Quiz;

public interface QuizPerformer {
	public boolean isShuffleQuestions();
	public void setShuffleQuestions(boolean shuffleQuestions);
	public boolean isShuffleCategories();
	public void setShuffleCategories(boolean shuffleCategories);
	public void performQuiz(Quiz q);
	public boolean shuffleCategories();
}
