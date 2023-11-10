package com.angaar.maie.components.quiz;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.angaar.maie.components.category.Category;

public class GeneralQuiz implements Quiz, Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	 private List<Category> categories;
	 private LocalDateTime startTime;
	 private LocalDateTime endTime;
	 
	 public GeneralQuiz() {
		 categories = new ArrayList<Category>();
	 }
	 public GeneralQuiz(List<Category> categories, String name) {
		 this.categories = categories;
		 this.name = name;
	 }
	@Override
	public List<Category> getCategories() {
		return categories;
	}
	
	public void addCategory(Category cat) {
		categories.add(cat);
	}
	@Override
	public void printQuiz() {
		System.out.println("Quiz name: "+name);
		categories.stream().forEach( cat -> cat.printCategory());
		
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
}
