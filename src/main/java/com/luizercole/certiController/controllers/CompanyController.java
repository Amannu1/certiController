package com.luizercole.certiController.controllers;

import com.luizercole.certiController.dto.CompanyDTO;
import com.luizercole.certiController.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    private ResponseEntity<List<CompanyDTO>> findAll(){
        List<CompanyDTO> list = companyService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
