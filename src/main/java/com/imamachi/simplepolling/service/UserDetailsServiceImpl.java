package com.imamachi.simplepolling.service;

import com.imamachi.simplepolling.model.User;
import com.imamachi.simplepolling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username == null || username.equals("")){
            throw new UsernameNotFoundException("ユーザ名が入力されていません。");
        }

        try{
            User user = userRepository.findByUsername(username);
            if(user == null){
                throw new UsernameNotFoundException("ユーザ情報が存在しません。" + username);
            }
            return new AuthenticationDetail(user);
        }catch(final Exception e){
            throw new RuntimeException(e);
        }
    }
}
