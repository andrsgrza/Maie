package com.angaar.maie.components.quiz;

import java.time.LocalDateTime;
import java.util.List;

import com.angaar.maie.components.category.Category;


public interface Quiz {
	List<Category> getCategories();
	void addCategory(Category cat);
	void printQuiz();
	void setName(String name);
	String getName();
	LocalDateTime getStartTime();
	void setEndTime(LocalDateTime endTime);
	void setStartTime(LocalDateTime startTime);
	LocalDateTime getEndTime();
	void setCategories(List<Category> categories);
}