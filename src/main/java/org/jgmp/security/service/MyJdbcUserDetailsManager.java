package org.jgmp.security.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class MyJdbcUserDetailsManager extends JdbcUserDetailsManager {
    @Autowired
    private LoginAttemptService loginAttemptService;

    public MyJdbcUserDetailsManager(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (loginAttemptService.isBlocked(username)) {
            System.out.println("=-=-USER " + username + " BLOCKED for 3 minutes=-==-=-=-=-=");
            throw new BadCredentialsException("blocked");
        }
        List<UserDetails> res = getJdbcTemplate().query(getUsersByUsernameQuery(), this::mapToUser, username);
        if (res.isEmpty()) {
            throw new UsernameNotFoundException("message");

        } else {
            return res.get(0);
        }
    }

    private UserDetails mapToUser(ResultSet rs, int rowNum) throws SQLException {
        String userName = rs.getString(1);
        String password = rs.getString(2);
        boolean enabled = rs.getBoolean(3);
        loadUserAuthorities(userName);
        return new User(userName, password, enabled, true, true, true,
                loadUserAuthorities(userName));
    }

}
