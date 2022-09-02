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
    public Expense getExpenseById(@PathVariable("id") Long id) {
        return expenseService.getExpenseById(id);
    }

//    using request param
    @DeleteMapping("/expenses")
    public String deleteExpenseById(@RequestParam("id") Long id) {
        expenseService.deleteExpenseById(id);
        return "Expense object has been deleted";
    }

    @PostMapping("/saveExpense")
    public Expense saveExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @PutMapping("/updateExpense/{id}")
    public Expense updateExpense(@RequestBody Expense expense, @PathVariable("id") Long id) {
        return expenseService.updateExpenseDetails(expense, id);
    }
}
