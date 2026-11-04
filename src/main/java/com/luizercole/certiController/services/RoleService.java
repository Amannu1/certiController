package com.luizercole.certiController.services;

import com.luizercole.certiController.dto.RoleDTO;
import com.luizercole.certiController.entities.Company;
import com.luizercole.certiController.entities.Role;
import com.luizercole.certiController.repositories.RoleRepository;
import com.luizercole.certiController.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll(){
        List<Role> list = roleRepository.findAll();

        return list.stream().map(x -> new RoleDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id){
        Optional<Role> obj = roleRepository.findById(id);
        Role entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return new RoleDTO(entity);
    }

    @Transactional
    public RoleDTO createRole(RoleDTO roleDTO){
        Role entity = new Role();
        entity.setAuthority(roleDTO.getAuthority());
        roleRepository.save(entity);
        return new RoleDTO(entity);
    }

    @Transactional
    public RoleDTO updateRole(RoleDTO roleDTO, Long id){
        try{
            Role entity = roleRepository.getReferenceById(id);
            entity.setAuthority(roleDTO.getAuthority());
            roleRepository.save(entity);
            return new RoleDTO(entity);
        }catch(EntityNotFoundException e){
            throw new EntityNotFoundException("Id not found:" + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteRole(Long id){
        if(roleRepository.existsById(id)){
            throw new EntityNotFoundException("Id not found" + id);
        }

        try{
            roleRepository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Database error.");
        }
    }
}
