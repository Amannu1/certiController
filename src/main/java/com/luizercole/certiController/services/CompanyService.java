package com.luizercole.certiController.services;

import com.luizercole.certiController.dto.CompanyDTO;
import com.luizercole.certiController.entities.Company;
import com.luizercole.certiController.repositories.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Transactional(readOnly = true)
    public List<CompanyDTO> findAll(){
        List<Company> list = repository.findAll();

        return list.stream().map(x -> new CompanyDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CompanyDTO findById(Long id){
        Optional<Company> obj = repository.findById(id);
        Company entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return new CompanyDTO(entity);
    }

    @Transactional
    public CompanyDTO createCompany(CompanyDTO companyDTO){
        Company entity = new Company();
        entity.setActive(companyDTO.getActive());
        entity.setName(companyDTO.getName());
        repository.save(entity);
        return new CompanyDTO(entity);
    }

    @Transactional
    public CompanyDTO updateCompany(CompanyDTO companyDTO, Long id){
        try{
            Company entity = repository.getReferenceById(id);
            entity.setName(companyDTO.getName());
            entity.setActive(companyDTO.getActive());
            repository.save(entity);
            return new CompanyDTO(entity);
        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException("Id not found:" + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteCompany(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Id not found" + id);
        }

        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Database error.");
        }
    }
}
