package com.ust.budget.repository;

import com.ust.budget.model.Budget;
import com.ust.budget.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends MongoRepository<Budget,String> {
    List<Budget> findAllByCreatedDate(LocalDate date);
    List<Budget> findAllByCategoryAndCreatedDateBetween(Category category, LocalDateTime start, LocalDateTime end);
    Optional<Budget> findTopByOrderByNoDesc();

    @Query("{ 'createdDate': { $gte: ?0, $lt: ?1 } }")
    List<Budget> findByMonth(LocalDateTime start, LocalDateTime end);
}
