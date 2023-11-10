package com.angaar.maie.components.question;

public interface Question extends Cloneable{
	String getQuestion();
	void setCorrect(boolean b);
	String getAnswer();
	void appendAnswer(String answer);
	void appendQuestion(String question);
	boolean hasQuestion();
	boolean hasAnswer();
	void setNull();
	Object clone() throws CloneNotSupportedException;
	void printQuestion();
	void setProvidedAnswer(String providedAnswer);
	boolean isCorrect();
}
