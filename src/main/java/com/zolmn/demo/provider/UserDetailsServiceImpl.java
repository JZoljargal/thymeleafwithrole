package com.zolmn.demo.provider;

import java.util.HashSet;
import java.util.Set;

import com.zolmn.demo.model.Role;
import com.zolmn.demo.model.User;
import com.zolmn.demo.service.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
        User user = userRepo.getUserByUsername(arg0);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        // return new org.springframework.security.core.userdetails.User(user.getName(),
        // user.getPassword(), grantedAuthorities);
        return new MyUserDetail(user);
    }

}
