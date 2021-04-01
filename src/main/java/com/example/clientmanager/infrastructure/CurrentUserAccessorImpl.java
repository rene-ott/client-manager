package com.example.clientmanager.infrastructure;

import com.example.clientmanager.data.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserAccessorImpl implements CurrentUserAccessor {

    public User getUser() {
        var principal = SecurityContextHolder.
                getContext()
                .getAuthentication()
                .getPrincipal();

        return ((AppUserPrincipal)principal).getUser();
    }
}
