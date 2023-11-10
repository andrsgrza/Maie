   package com.angaar.maie.service.quizperfromer;

import java.time.LocalDateTime;
import java.util.List;

import com.angaar.maie.components.category.Category;
import com.angaar.maie.components.question.Question;
import com.angaar.maie.components.quiz.Quiz;
import com.angaar.maie.util.io.Prompter;

public class GeneralQuizPerformer implements QuizPerformer{
	private boolean shuffleQuestions;
	private boolean shuffleCategories;
	List<Category> catList;
	boolean mode1 = false;
	boolean mode2 = false;
	boolean mode3 = false;
	final String ASK_FEEDBACK = "\tIf you consider your answer as correct, enter 'Y'"; 
	  
	private static Prompter prompt= new Prompter(System.in);	
	
	
	public boolean isShuffleQuestions() {
		return shuffleQuestions;
	}
	public void setShuffleQuestions(boolean shuffleQuestions) {
		this.shuffleQuestions = shuffleQuestions;
	}
	public boolean isShuffleCategories() {
		return shuffleCategories;
	}
	public void setShuffleCategories(boolean shuffleCategories) {
		this.shuffleCategories = shuffleCategories;
	}

	@Override
	public void performQuiz(Quiz quiz) {
		quiz.setStartTime(LocalDateTime.now());
		quiz.getCategories().stream().forEach(cat -> askCategoryQuestions(cat));
		quiz.setEndTime(LocalDateTime.now());
	}
	
	public void askQuestion(Question q) {
		q.setProvidedAnswer(prompt.promptString(q.getQuestion()));
	}
	public void askQuestionWithAnswerNoFeedBack(Question q) {
		q.setProvidedAnswer(prompt.promptString(q.getQuestion()));
		 System.out.println("The correct answer is:\n"+q.getAnswer());
	}
	 public void askQuestionWithAnswerAndFeedBack(Question q) {
		 q.setProvidedAnswer(prompt.promptString(q.getQuestion()));
		 System.out.println("The correct answer is:\n"+q.getAnswer());
		 String feedback = prompt.promptString(ASK_FEEDBACK);
		 if( feedback.stripIndent().equals("Y")) {
			 q.setCorrect(true);
		 }
	}
	 public void askCategoryQuestions(Category cat) {
		 System.out.println(cat.getName());
		 cat.getQuestions().stream().forEach( que -> askQuestionWithAnswerAndFeedBack(que));
	 }
	@Override
	public boolean shuffleCategories() {
		// TODO Auto-generated method stub
		return false;
	}
}