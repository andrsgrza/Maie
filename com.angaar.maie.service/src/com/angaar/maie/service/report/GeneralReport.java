package com.angaar.maie.service.report;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.angaar.maie.components.category.Category;
import com.angaar.maie.components.quiz.Quiz;

public class GeneralReport {
	private int questionsAttempted;
	private int correctAnswers;
	private int incorrectAnswers;
	private float score;
	private Map<String, CategoryReport> reportPerCategory = new LinkedHashMap<>();
	Quiz quiz;
	
	public GeneralReport(Quiz quiz) {
		this.quiz = quiz;
		calculateCategoriesReport();
		populateAttributes();
		calculateScore();		
	}
	
	private void calculateScore() {
		this.score = ((float)correctAnswers/(float)questionsAttempted)*100;
	}
	private void calculateCategoriesReport() {
		quiz.getCategories().forEach(cat -> generateCategoryReport(cat));
	}
	private void generateCategoryReport(Category cat) {
		CategoryReport catRep = new CategoryReport();
		catRep.setAttempted(cat.getQuestions().size());
		catRep.setCorrect(cat.getQuestions().stream().filter( q -> q.isCorrect()).count());
		catRep.setIncorrect(cat.getQuestions().stream().filter( q -> !q.isCorrect()).count());
		reportPerCategory.put(cat.getName(), catRep);
		
	}
	private class CategoryReport{
		private long attempted;
		private long correct;
		private long incorrect;
		
		public long getAttempted() {
			return attempted;
		}
		public void setAttempted(long attempted) {
			this.attempted = attempted;
		}
		public long getCorrect() {
			return correct;
		}
		public void setCorrect(long correct) {
			this.correct = correct;
		}
		public long getIncorrect() {
			return incorrect;
		}
		public void setIncorrect(long incorrect) {
			this.incorrect = incorrect;
		}
		
	}
	public void populateAttributes() {
		Iterator<Map.Entry<String, CategoryReport>> iterator = reportPerCategory.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, CategoryReport> entry = iterator.next();
            CategoryReport value = entry.getValue();
            questionsAttempted += value.getAttempted();
            correctAnswers += value.getCorrect();
            incorrectAnswers += value.getIncorrect();
        }
	}
	public void printReport() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");

		System.out.println("Quiz Report: " + quiz.getName());
        System.out.println("Start Time: " + quiz.getStartTime().format(formatter));
        System.out.println("End Time: " + quiz.getEndTime().format(formatter));
        System.out.println("Total Questions Attempted: " + questionsAttempted);
        System.out.println("Correct Answers: " + correctAnswers);
        System.out.println("Incorrect Answers: " + incorrectAnswers);
        System.out.println("Score: " + score + "%");

        Iterator<Map.Entry<String, CategoryReport>> iterator = reportPerCategory.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, CategoryReport> entry = iterator.next();
            String key = entry.getKey();
            CategoryReport value = entry.getValue();
            float attempted = value.getAttempted();
            float correct = value.getCorrect();
            float incorrect = value.getIncorrect();
            System.out.println("Category " + key + 
            		"\n\tAttempted Questions:\t" + attempted
            		+"\n\tCorrect Answers:\t" + correct
            		+"\n\tIncorrect Answers:\t" + incorrect
            		+"\n\tCategory Performance:\t" + (correct/attempted)*100+"%"
            		);
        }
    
	}
	
}
