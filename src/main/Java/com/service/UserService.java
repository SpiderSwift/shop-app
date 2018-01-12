package com.service;

import com.DAO.DAO;
import com.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
//
@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private DAO<com.object.User> userDAO;


    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.object.User user = null;
        try {
            user = userDAO.readById(s);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new User(user.getUserName(), user.getUserPass(), grantedAuthorities);
    }



}
