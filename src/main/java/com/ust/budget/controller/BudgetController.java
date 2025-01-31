package com.ust.budget.controller;

import com.ust.budget.model.Budget;
import com.ust.budget.model.Category;
import com.ust.budget.service.BudgetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/budget")
public class BudgetController {
    @Autowired
    BudgetServiceImpl budgetService;

    @GetMapping
    public ResponseEntity<List<Budget>> getAllTransaction(){
        return new ResponseEntity<>(budgetService.getAllTransactions(),HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<Optional<List<Budget>>> findByDate(@RequestParam String date){
        return new ResponseEntity<>(budgetService.findByDate(date),HttpStatus.OK);
    }

    @GetMapping("/spend/total")
    public ResponseEntity<Double> getTotalAmount(){
        return new ResponseEntity<>(budgetService.totalAmount(), HttpStatus.OK);
    }

    @GetMapping("/spend/category")
    public ResponseEntity<Map<Category,Double>> getTotalByCategory(){
        return new ResponseEntity<>(budgetService.totalByCategory(),HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Budget> addNewBudget(@RequestBody Budget budget){
        budgetService.addBudget(budget);
        return new ResponseEntity<>(budget,HttpStatus.OK);
    }
}
