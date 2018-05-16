package com.example.yc_server.repository;

import com.example.yc_server.domain.GradeTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamGradeRepository extends JpaRepository<GradeTeam,Integer> {
}
