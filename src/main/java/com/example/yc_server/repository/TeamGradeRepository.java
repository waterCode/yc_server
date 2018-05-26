package com.example.yc_server.repository;

import com.example.yc_server.domain.GradeTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamGradeRepository extends JpaRepository<GradeTeam,Integer> {

    public List<GradeTeam> findByCaptionNameAndScorerName(String captionName,String scorerName);
}
