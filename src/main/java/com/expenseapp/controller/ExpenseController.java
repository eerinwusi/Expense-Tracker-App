package com.expenseapp.controller;

import com.expenseapp.model.Expense;
import com.expenseapp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

//    using path variable
    @GetMapping("/expenses/{id}")
    public String getExpenseById(@PathVariable("id") Long id) {
        return "The expense is " + id;
    }

//    using request param.
    @DeleteMapping("/expenses")
    public String deleteExpenseById(@RequestParam("id") Long id) {
        return "Delete object with id - " + id;
    }
}
