package com.example.clientmanager.infrastructure;

import com.example.clientmanager.data.entities.User;

public interface CurrentUserAccessor {
    User getUser();
}
