package com.imamachi.simplepolling.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.stream.Collectors;

public class AuthenticationDetail extends User {

    public AuthenticationDetail(com.imamachi.simplepolling.model.User user){
        super(
                user.getUsername(), // ユーザ名
                user.getPassword(), // パスワード
                true,   // ユーザが有効かどうか
                true,   // アカウントの有効期限が過ぎていないかどうか
                true,   // アカウントの資格情報が有効期限切れでないかどうか
                true,   // アカウントがロックされていないかどうか
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                        .collect(Collectors.toList())   // 認可情報のリスト
        );
    }
}
