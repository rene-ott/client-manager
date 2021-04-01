package com.example.clientmanager.services;

import com.example.clientmanager.data.entities.Client;

import java.util.List;

public interface ClientService {
    void updateClient(Client client);
    void addClient(Client client);
    List<Client> getClients();
    Client getById(int id);

}
