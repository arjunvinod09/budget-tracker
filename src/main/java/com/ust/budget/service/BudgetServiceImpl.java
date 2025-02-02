package com.ust.budget.service;

import com.ust.budget.model.Budget;
import com.ust.budget.model.Category;
import com.ust.budget.model.Type;
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
        List<Budget> budgets = budgetRepository.findAll();
        double total = 0.0;
        for(Budget budget : budgets){
            System.out.println(total);
            if(budget.getType()== Type.DEBIT){
                total += budget.getAmount();
            }
            else if(budget.getType() == Type.CREDIT){
                total -= budget.getAmount();
            }
        }
        return total;
    }

    //TODO make every calculation accurate based on DEBIT or CREDIT
    @Override
    public Map<Category, Double> totalByCategory() {
        return budgetRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Budget::getCategory,
                        Collectors.summingDouble(Budget::getAmount)
                ));
    }

    @Override
    public Budget addBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public List<Budget> getAllTransactions() {
        return budgetRepository.findAll();
    }

    @Override
    public List<Budget> findByDate(String date) {
        return budgetRepository.findAllByCreatedDate(LocalDate.parse(date));
    }

    @Override
    public List<Budget> findByCategory(Category category) {
        return budgetRepository.findAllByCategory(category);
    }

//    private long getNextBudgetId() {
//        Optional<Budget> latestBudgetOptional = budgetRepository.findTopByOrderByIdDesc();
//
//        if (latestBudgetOptional.isPresent()) {
//            Budget latestBudget = latestBudgetOptional.get();
//            Long latestId = latestBudget.getNo();
//            return latestId++;
//        } else {
//            return 1;
//        }
//    }
}
