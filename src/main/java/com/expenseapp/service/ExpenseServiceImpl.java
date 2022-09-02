package com.expenseapp.service;

import com.expenseapp.model.Expense;
import com.expenseapp.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            return expense.get();
        }
        throw new RuntimeException("The expense object is not found with id "+id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            expenseRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("The expense object with id "+id +" " + "cannot be found");
        }

    }
//
//    @Override
//    public Expense saveExpense(Expense expense) {
//        return expenseRepository.save(expense);
//    }
}
