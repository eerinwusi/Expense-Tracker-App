package com.expenseapp.service;

import com.expenseapp.model.Expense;

import java.util.List;

public interface ExpenseService {
    List<Expense> getAllExpenses();
}
