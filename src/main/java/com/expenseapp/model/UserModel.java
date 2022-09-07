package com.expenseapp.model;

import lombok.Data;

@Data
public class UserModel {
    private String name;

    private String email;

    private String password;

    private Long age = 0L;
}
