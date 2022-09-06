package com.expenseapp.repository;

import com.expenseapp.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

//    The equivalent for SQL would be - SELECT * FROM tbl_expenses WHERE category=?
    Page<Expense> findByCategory(String Category, Pageable page);

//    The equivalent for SQL would be - SELECT * FROM tbl_expenses WHERE name LIKE '%keyword%'
    Page<Expense> findByNameContaining(String keyword, Pageable page);

//    The equivalent for SQL would be - SELECT * FROM tbl_expenses WHERE date BETWEEN 'startDate' AND 'endDate'
    Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);
}
