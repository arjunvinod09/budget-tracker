package com.ust.budget.service;

import com.ust.budget.model.Budget;
import com.ust.budget.model.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BudgetService {

    Double totalAmount();

    Map<Category,Double> totalByCategory();

    Budget addBudget(Budget budget);

    List<Budget> getAllTransactions();

    List<Budget> findByDate(String date);

    List<Budget> findByCategory(Category category);
}