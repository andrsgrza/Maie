package com.angaar.maie.components.category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.angaar.maie.components.question.GeneralQuestion;
import com.angaar.maie.components.question.Question;

public class GeneralCategory implements Category, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private List<Question> questions;
    
    public GeneralCategory(String name) {
    	questions = new ArrayList<Question> ();
    	this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) throws CloneNotSupportedException {    	
        questions.add((GeneralQuestion) question.clone());
    }
    
    public void setUnamed() {
    	this.name=null;    	
    }
        
    public boolean isUnamed() {
    	return this.name==null;
    }
    public void reset() {
    	this.name=null;
    	this.questions.clear();;
    }
    public void printCategory() {
    	System.out.println("Category name: "+ name);
    	questions.forEach(q -> q.printQuestion());
    }
    public boolean hasIncorrect() {
    	if(questions.stream().filter(ques -> !ques.isCorrect()).count() > 0) {
    		return true;
    	}
    	return false;
    }
	@Override
	public boolean hasQuestions() {
		return !this.questions.isEmpty();		
	}
}

