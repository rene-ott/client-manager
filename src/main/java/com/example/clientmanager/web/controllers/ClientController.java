package com.example.clientmanager.web.controllers;

import com.example.clientmanager.data.entities.Client;
import com.example.clientmanager.data.repositories.CountryRepository;
import com.example.clientmanager.services.ClientService;
import com.example.clientmanager.web.forms.ClientForm;
import com.example.clientmanager.web.models.CountryModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ClientController {

    private final ModelMapper modelMapper;
    private final ClientService clientService;
    private final CountryRepository countryRepository;

    public ClientController(ModelMapper modelMapper,
                            ClientService clientService,
                            CountryRepository countryRepository) {
        this.modelMapper = modelMapper;
        this.clientService = clientService;
        this.countryRepository = countryRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        var clientModels = clientService
                .getClients()
                .stream()
                .map(it -> modelMapper.map(it, ClientForm.class))
                .collect(Collectors.toList());

        model.addAttribute("clients", clientModels);

        return "index";
    }

    @GetMapping("/client/add")
    public String addClient(Model model) {
        model.addAttribute("clientForm", new ClientForm());
        model.addAttribute("countries", getCountryModels());

        return "client";
    }

    @PostMapping("/client/add")
    public String addClient(@Valid @ModelAttribute(value = "clientForm") ClientForm clientForm,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("countries", getCountryModels());
            return "client";
        }

        clientService.addClient(modelMapper.map(clientForm, Client.class));

        return "redirect:/";
    }

    @GetMapping("/client/edit/{id}")
    public String editClient(@PathVariable("id") int id,
                             Model model) {
        var client = clientService.getById(id);
        model.addAttribute("clientForm", modelMapper.map(client, ClientForm.class));
        model.addAttribute("countries", getCountryModels());

        return "client";
    }

    @PostMapping("/client/edit/{id}")
    public String editClient(@PathVariable("id") int id,
                             @Valid @ModelAttribute(value = "clientForm") ClientForm clientForm,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clientForm", clientForm);
            model.addAttribute("countries", getCountryModels());

            return "client";
        }

        clientService.updateClient(modelMapper.map(clientForm, Client.class));
        return "redirect:/";
    }

    private List<CountryModel> getCountryModels() {
        return countryRepository
                .findAll()
                .stream()
                .map(it -> modelMapper.map(it, CountryModel.class))
                .collect(Collectors.toList());
    }
}
