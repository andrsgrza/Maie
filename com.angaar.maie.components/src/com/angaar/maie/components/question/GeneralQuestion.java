package com.angaar.maie.components.question;

import java.io.Serializable;

public class GeneralQuestion implements Question, Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String question;
    private String answer;
    private boolean isCorrect = false;    
	    
    public GeneralQuestion() {
    	
    }

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	public void setNull() {
		this.question="";
		this.answer="";
	}
	
	public void appendQuestion(String question) {
		if(this.question == null || this.question.isBlank() || this.question.isEmpty()) {
			this.question=question;			
		}else {
			this.question+="\n"+question;
		}
	}
	
	public void appendAnswer(String answer) {
		if(this.answer == null || this.answer.isBlank()) {
			this.answer=answer;			
		}else {
			this.answer+="\n"+answer;
		}
	}
	
	public boolean hasQuestion() {
		return !isEmptyOrNull(this.question);
	}
	public boolean hasAnswer() {
		return !isEmptyOrNull(this.answer);
	}
	@Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); 
        }
	}
	public void printQuestion() {
		System.out.println(question+"\n"+answer);
	}
	public void setProvidedAnswer(String providedAnswer) {
	}
	
	public boolean isEmptyOrNull(String s) {
		if(s == null) {
			return true;
		}if( s.isBlank() || s.isEmpty()){
			return true;
		}
		return false;
	}

}
