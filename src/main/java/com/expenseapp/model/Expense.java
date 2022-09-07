package com.expenseapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expenses_name")
    @NotBlank(message = "Expense name must not be blank")
    @Size(min = 3, message = "Expense name must be at least 3 characters")
    private String name;

    private String description;

    @Column(name = "expenses_amount")
    @NotNull(message = "amount must not be null")
    private BigDecimal amount;

    @NotBlank(message = "category must not be blank")
    private String category;

    private Date date;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) // this means when a user is deleted, all its associated expenses
//    are deleted too since the expenses don't exist without the user
    @JsonIgnore // this means whenever we fetch the expense, we're not going to fetch the user, it will be hidden
    private User user;
}
