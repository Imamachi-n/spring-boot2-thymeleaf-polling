package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.UserForm;
import com.imamachi.simplepolling.model.User;

import java.util.List;

public interface UserService {

    UserForm getUserById(Integer userId);

    User findUserByUsername(String username);

    void saveUser(User user, boolean isAdmin);

    void updateUser(UserForm userForm);

    int deleteUser(List<Integer> userIds);
}
