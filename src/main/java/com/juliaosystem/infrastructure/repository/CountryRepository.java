package com.juliaosystem.infrastructure.repository;

import com.juliaosystem.infrastructure.entitis.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer> {
}
