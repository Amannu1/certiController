package com.luizercole.certiController.services;

import com.luizercole.certiController.dto.CompanyDTO;
import com.luizercole.certiController.entities.Company;
import com.luizercole.certiController.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    public List<CompanyDTO> findAll(){
        List<Company> list = repository.findAll();

        return list.stream().map(x -> new CompanyDTO(x)).collect(Collectors.toList());
    }



}
