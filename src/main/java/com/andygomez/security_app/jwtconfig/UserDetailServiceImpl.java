package com.andygomez.security_app.jwtconfig;

import com.andygomez.security_app.flow.model.UserModel;
import com.andygomez.security_app.flow.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel existUser = repository.findUserModelByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        existUser.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        existUser.getRoles().stream()
                .flatMap(role -> role.getPermissionModels().stream())
                .forEach(permissionModel -> authorityList.add(new SimpleGrantedAuthority(permissionModel.getName())));

        return new User(existUser.getUsername(),
                existUser.getPassword(),
                existUser.isEnable(),
                existUser.isCredentialNoExpired(),
                existUser.isAccountNoExpired(),
                existUser.isAccountNoLocked(),
                authorityList
        );
    }
}
