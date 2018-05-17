package com.example.yc_server.controller;

import com.example.yc_server.repository.CompetitionFromRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("excel")
public class ExcelController {

    @Autowired
    private CompetitionFromRepository competitionFromRepository;


}
