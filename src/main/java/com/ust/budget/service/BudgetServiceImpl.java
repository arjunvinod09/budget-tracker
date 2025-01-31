package com.ust.budget.service;

import com.ust.budget.model.Budget;
import com.ust.budget.model.Category;
import com.ust.budget.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BudgetServiceImpl implements BudgetService{

    @Autowired
    BudgetRepository budgetRepository;

    @Override
    public Double totalAmount() {
        return budgetRepository.findAllAmount().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public Map<Category, Double> totalByCategory() {
        return budgetRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Budget::getCategory,
                        Collectors.summingDouble(Budget::getAmount)
                ));
    }

    @Override
    public void addBudget(Budget budget) {
        budgetRepository.save(budget);
    }

    @Override
    public List<Budget> getAllTransactions() {
        return budgetRepository.findAll();
    }

    @Override
    public Optional<List<Budget>> findByDate(String date) {
        return budgetRepository.findByCreatedDate(LocalDate.parse(date));
    }

    @Override
    public Optional<List<Budget>> findByCategory(Category category) {
        return budgetRepository.findByCategory(category);
    }
}
