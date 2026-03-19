package com.tracker.expensetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*") // Allow requests from any frontend
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    // 1. Get all expenses
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

    // 2. Add an expense
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Expense addExpense(@RequestBody Expense expense) {
        if (expense.getDate() == null) {
            expense.setDate(new Date());
        }
        return expenseRepository.save(expense);
    }

    // 3. Delete an expense
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable String id) {
        if (!expenseRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense not found");
        }
        expenseRepository.deleteById(id);
    }
}
