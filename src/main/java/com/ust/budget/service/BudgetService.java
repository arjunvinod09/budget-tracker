package com.ust.budget.service;

import com.ust.budget.model.Budget;
import com.ust.budget.model.Category;

import java.util.List;
import java.util.Map;

public interface BudgetService {

    Double totalAmount();

    Double totalDaily(int day);

    Map<Category,Double> totalByCategory();

    Budget addBudget(Budget budget);

    List<Budget> getAllTransactions();

    List<Budget> findByDate(String date);

    List<Budget> findByCategory(Category category);

    List<Budget> findThisMonthsSpend();

    List<Budget> dailyTransactions(int day);
}