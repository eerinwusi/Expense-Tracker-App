package com.expenseapp.repository;

import com.expenseapp.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

//    The equivalent for SQL would be - SELECT * FROM tbl_expenses WHERE category=?
    Page<Expense> findByCategory(String Category, Pageable page);
}
