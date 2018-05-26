package com.example.yc_server.repository;

import com.example.yc_server.domain.RegistrationForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompetitionFromRepository extends JpaRepository<RegistrationForm,Long> {

    @Override
    Optional<RegistrationForm> findById(Long aLong);
}
