package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.form.UserForm;
import com.imamachi.simplepolling.model.Role;
import com.imamachi.simplepolling.model.User;
import com.imamachi.simplepolling.repository.RoleRepository;
import com.imamachi.simplepolling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserForm getUserById(Integer userId){

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            Boolean isAdmin = user.get().getRoles()
                    .stream().anyMatch(role -> role.getRole().name().equals(Role.RoleName.ADMIN.name()));
            return new UserForm(userId, user.get().getUsername(), isAdmin, user.get().getVersion());
        }else{
            return null;
        }
    }

    @Override
    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void saveUser(User user, boolean isAdmin){
        // BCryptで暗号化してパスワードとして保存
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (isAdmin){
            user.setRoles(Arrays.asList(roleRepository.findByRole(Role.RoleName.ADMIN),
                    roleRepository.findByRole(Role.RoleName.USER)));
        }else{
            user.setRoles(Arrays.asList(roleRepository.findByRole(Role.RoleName.USER)));
        }

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(UserForm userForm){

        Optional<User> userO = userRepository.findById(userForm.getUserId());
        if (!userO.isPresent()) return;

        User user = userO.get();
        if (userForm.getIsAdmin()){
            user.setRoles(Arrays.asList(roleRepository.findByRole(Role.RoleName.ADMIN),
                    roleRepository.findByRole(Role.RoleName.USER)));
        }else{
            user.setRoles(Arrays.asList(roleRepository.findByRole(Role.RoleName.USER)));
        }

        userRepository.save(user);
    }

    @Override
    public int deleteUser(List<Integer> userIds){
        return userRepository.deleteUserByUserId(userIds);
    }
}
