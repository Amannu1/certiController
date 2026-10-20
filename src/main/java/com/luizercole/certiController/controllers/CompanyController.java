package com.luizercole.certiController.controllers;

import com.luizercole.certiController.dto.CompanyDTO;
import com.luizercole.certiController.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> findAll(){
        List<CompanyDTO> list = companyService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findById(@PathVariable Long id){
        CompanyDTO dto = companyService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody CompanyDTO companyDTO){
        companyDTO = companyService.createCompany(companyDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(companyDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(companyDTO);
    }
}
