package com.ust.budget.service;

import com.ust.budget.model.Budget;
import com.ust.budget.model.Category;
import com.ust.budget.model.Type;
import com.ust.budget.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BudgetServiceImpl implements BudgetService{

    @Autowired
    BudgetRepository budgetRepository;
    ArrayList<Long> requiredNo = new ArrayList<>();

    private final LocalDateTime start = YearMonth.of(2025, LocalDate.now().minusMonths(1).getMonth()).atEndOfMonth().atTime(14,00,00);
    private final LocalDateTime end = YearMonth.of(2025, LocalDate.now().getMonth()).atEndOfMonth().atTime(23, 59, 59);

    @Override
    public Double totalAmount() {
        for(int i=0;i<100;i++){
            requiredNo.add(i+1L);
        }
        List<Budget> budgets = budgetRepository.findByMonth(start,end);
        double total = 0.0;
        for(Budget budget : budgets){
            if(requiredNo.contains(budget.getNo())){
                if(budget.getType()== Type.DEBIT){
                    total += budget.getAmount();
                }
                else if(budget.getType() == Type.CREDIT){
                    total -= budget.getAmount();
                }
            }
        }
        return total;
    }

    @Override
    public Double totalDaily(int day) {
        LocalDateTime startOfDay = LocalDate.now().withDayOfMonth(day).atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().withDayOfMonth(day).atTime(LocalTime.MAX);
        List<Budget> budgets = budgetRepository.findByMonth(startOfDay,endOfDay);
        double total = 0.0;
        for(Budget budget : budgets){
            if(budget.getCategory()==Category.SAVINGS){
                continue;
            }
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
        return budgetRepository.findByMonth(start,end).stream()
                .collect(Collectors.groupingBy(
                        Budget::getCategory,
                        Collectors.summingDouble(budget ->
                                "CREDIT".equalsIgnoreCase(budget.getType().toString())
                                        ? -budget.getAmount()
                                        : budget.getAmount()
                        )
                ));
    }

    @Override
    public Budget addBudget(Budget budget) {
        budget.setNo(getNextBudgetId());
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
        return budgetRepository.findAllByCategoryAndCreatedDateBetween(category,start,end);
    }

    @Override
    public List<Budget> findThisMonthsSpend() {
        return budgetRepository.findByMonth(start,end);
    }

    private long getNextBudgetId() {
        Optional<Budget> latestBudgetOptional = budgetRepository.findTopByOrderByNoDesc();
        if (latestBudgetOptional.isPresent()) {
            Budget latestBudget = latestBudgetOptional.get();
            Long latestId = latestBudget.getNo();
            return latestId + 1;
        } else {
            return 1;
        }
    }
}
