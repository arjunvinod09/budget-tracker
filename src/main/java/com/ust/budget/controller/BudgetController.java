package com.ust.budget.controller;

import com.ust.budget.model.Budget;
import com.ust.budget.model.Category;
import com.ust.budget.service.BudgetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/budget")
public class BudgetController {
    @Autowired
    BudgetServiceImpl budgetService;

    @GetMapping
    public ResponseEntity<List<Budget>> getAllTransaction(){
//        return new ResponseEntity<>(budgetService.getAllTransactions(),HttpStatus.OK);
        return new ResponseEntity<>(budgetService.findThisMonthsSpend(),HttpStatus.OK);
    }

    //broken after merging date and time
    @GetMapping("/date")
    public ResponseEntity<List<Budget>> findByDate(@RequestParam String date){
        return new ResponseEntity<>(budgetService.findByDate(date),HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Budget>> findByCategory(@RequestParam Category category){
        return new ResponseEntity<>(budgetService.findByCategory(category),HttpStatus.OK);
    }

    @GetMapping("/spend/total")
    public ResponseEntity<Double> getTotalAmount(){
        return new ResponseEntity<>(budgetService.totalAmount(), HttpStatus.OK);
    }

    @GetMapping("/spend/daily")
    public ResponseEntity<Double> getTotalAmountDaily(){
        return new ResponseEntity<>(budgetService.totalDaily(), HttpStatus.OK);
    }

    @GetMapping("/spend/category")
    public ResponseEntity<Map<Category,Double>> getTotalByCategory(){
        return new ResponseEntity<>(budgetService.totalByCategory(),HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Budget> addNewBudget(@RequestBody Budget budget){
        return new ResponseEntity<>(budgetService.addBudget(budget),HttpStatus.OK);
    }
}
