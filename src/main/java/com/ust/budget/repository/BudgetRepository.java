package com.ust.budget.repository;

import com.ust.budget.model.Budget;
import com.ust.budget.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends MongoRepository<Budget,String> {
    List<Budget> findAllByCreatedDate(LocalDate date);
    List<Budget> findAllByCategory(Category category);
    Optional<Budget> findTopByOrderByNoDesc();
}
