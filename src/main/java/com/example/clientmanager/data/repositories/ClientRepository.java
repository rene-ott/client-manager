package com.example.clientmanager.data.repositories;

import com.example.clientmanager.data.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAllByUserId(int userId);
}
