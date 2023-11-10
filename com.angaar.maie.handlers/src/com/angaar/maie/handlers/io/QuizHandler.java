package com.angaar.maie.handlers.io;

import java.util.ArrayList;
import java.util.List;

import com.angaar.maie.components.category.Category;
import com.angaar.maie.components.category.GeneralCategory;
import com.angaar.maie.components.question.Question;
import com.angaar.maie.components.quiz.GeneralQuiz;
import com.angaar.maie.components.quiz.Quiz;

public class QuizHandler {
	public Quiz createRedoQuiz(Quiz q){
		List<Category> filteredCategories = new ArrayList<>();
		q.getCategories().forEach(cat -> {cat = filterQuestions(cat);
		if(cat.hasQuestions()) {
			filteredCategories.add(cat);
		}		
		});
		Quiz redoQuiz = new GeneralQuiz(filteredCategories,q.getName());		
		return redoQuiz;
	}
	public Category filterQuestions(Category cat) {
		Category filteredCategory = new GeneralCategory(cat.getName());		
		List<Question> filteredQuestions = cat.getQuestions().stream().filter( ques -> !ques.isCorrect()).toList();
		filteredCategory.setQuestions(filteredQuestions);
		return filteredCategory; 
	}

}
