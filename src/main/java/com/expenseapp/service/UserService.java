package com.expenseapp.service;

import com.expenseapp.model.User;
import com.expenseapp.model.UserModel;

public interface UserService {
    User createUser(UserModel user);

    User readUser(Long id);

    User updateUser(UserModel user, Long id);
}
