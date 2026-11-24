package com.luizercole.certiController.services;

import com.luizercole.certiController.dto.RoleDTO;
import com.luizercole.certiController.dto.UserDTO;
import com.luizercole.certiController.entities.Role;
import com.luizercole.certiController.entities.User;
import com.luizercole.certiController.projections.UserDetailsProjection;
import com.luizercole.certiController.repositories.RoleRepository;
import com.luizercole.certiController.repositories.UserRepository;
import com.luizercole.certiController.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = userRepository.searchUserAndRolesByUsername(username);
        if(result.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        User user = new User();
        user.setName(username);
        user.setPassword(result.get(0).getPassword());
        for(UserDetailsProjection projection : result){
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }
    @Transactional(readOnly = true)
    public List<UserDTO> findAll(){
        List<User> list = userRepository.findAll();
        return list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO){
        User entity = new User();
        copyDtoToEntity(userDTO, entity);
        entity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        entity = userRepository.save(entity);
        return new UserDTO(entity);

    }

    private void copyDtoToEntity(UserDTO userDTO, User entity){
        entity.setName(userDTO.getName());
        entity.setActive(userDTO.getActive());
        entity.getRoles().clear();
        for(RoleDTO roleDto : userDTO.getRoles()){
            Role role = roleRepository.findById(roleDto.getId()).orElseThrow(() -> new EntityNotFoundException("Role not found."));
            entity.getRoles().add(role);
        }
    }
}
