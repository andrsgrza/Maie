package com.angaar.maie.components.category;

import java.util.List;

import com.angaar.maie.components.question.Question;

public interface Category {
	String getName();
	List<Question> getQuestions();
	void addQuestion(Question question) throws CloneNotSupportedException;
	void printCategory();
	void setQuestions(List<Question> questions);
	boolean hasQuestions();
}
