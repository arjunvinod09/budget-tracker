package com.ust.budget.repository;

import com.ust.budget.model.Budget;
import com.ust.budget.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {

    @Query("SELECT b.amount FROM Budget b")
    List<Double> findAllAmount();
    Optional<List<Budget>> findByCreatedDate(LocalDate date);
    Optional<List<Budget>> findByCategory(Category category);
}
