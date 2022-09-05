package com.expenseapp.controller;

import com.expenseapp.model.Expense;
import com.expenseapp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page) {
        return expenseService.getAllExpenses(page).toList();
    }

//    using path variable
//    no need to add http status code here since postman automatically returns 200 already.
    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id) {
        return expenseService.getExpenseById(id);
    }

//    using request param
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses")
    public String deleteExpenseById(@RequestParam("id") Long id) {
        expenseService.deleteExpenseById(id);
        return "Expense object has been deleted";
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/saveExpense")
    public Expense saveExpense(@RequestBody Expense expense) {
        return expenseService.saveExpense(expense);
    }

    @PutMapping("/updateExpense/{id}")
    public Expense updateExpense(@RequestBody Expense expense, @PathVariable("id") Long id) {
        return expenseService.updateExpenseDetails(expense, id);
    }
}
