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

    void addBudget(Budget budget);

    List<Budget> getAllTransactions();

    Optional<List<Budget>> findByDate(String date);

    Optional<List<Budget>> findByCategory(Category category);
}