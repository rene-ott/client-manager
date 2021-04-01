package com.example.clientmanager.services;

import com.example.clientmanager.data.entities.Client;
import com.example.clientmanager.data.repositories.ClientRepository;
import com.example.clientmanager.infrastructure.ClientNotFoundException;
import com.example.clientmanager.infrastructure.CurrentUserAccessor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final CurrentUserAccessor currentUserAccessor;
    private final ClientRepository clientRepository;

    public ClientServiceImpl(CurrentUserAccessor currentUserAccessor, ClientRepository clientRepository) {
        this.currentUserAccessor = currentUserAccessor;
        this.clientRepository = clientRepository;
    }

    public void updateClient(Client client) {
        var existingClient = findClientById(client.getId());
        var currentUserId = currentUserAccessor.getUser().getId();

        if (existingClient.getUserId() != currentUserId)
            throw new ClientNotFoundException("Modifying another user's client is forbidden");

        existingClient.update(client);
        clientRepository.save(existingClient);
    }

    public void addClient(Client client) {
        if (client.getId() > 0)
            throw new IllegalStateException("Adding a client with existing id is forbidden");

        client.setUserId(currentUserAccessor.getUser().getId());
        clientRepository.save(client);
    }

    public List<Client> getClients() {
        return clientRepository.findAllByUserId(currentUserAccessor.getUser().getId());
    }

    public Client getById(int id) {
        var client = findClientById(id);
        var currentUserid = currentUserAccessor.getUser().getId();

        if (client.getUserId() != currentUserid)
            throw new ClientNotFoundException("Viewing another user's client is forbidden");

        return client;
    }

    private Client findClientById(int id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client with id [" + id + "] not found"));
    }
}
