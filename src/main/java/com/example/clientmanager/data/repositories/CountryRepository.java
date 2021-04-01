package com.example.clientmanager.data.repositories;

import com.example.clientmanager.data.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
