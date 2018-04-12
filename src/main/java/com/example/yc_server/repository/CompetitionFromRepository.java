package com.example.yc_server.repository;

import com.example.yc_server.domain.RegistrationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionFromRepository extends JpaRepository<RegistrationForm,Integer> {
}
