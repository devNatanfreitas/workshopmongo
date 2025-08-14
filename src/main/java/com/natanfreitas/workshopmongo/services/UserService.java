package com.natanfreitas.workshopmongo.services;

import com.natanfreitas.workshopmongo.domain.User;
import com.natanfreitas.workshopmongo.dto.UserDTO;
import com.natanfreitas.workshopmongo.repository.UserRepository;
import com.natanfreitas.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repo.findById(id);
        return obj
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
    public User insert(User obj){
        return repo.insert(obj);
    }
    public void delete(String id){
        findById(id);
        repo.deleteById(id);
    }
    public User update(User obj){
        Optional<User> optinalUser = repo.findById(obj.getId());

        if (optinalUser.isPresent()){
            User newObj = optinalUser.get();
            updateData(newObj, obj);
            return repo.save(newObj);
        } else {
        throw new ObjectNotFoundException("Objeto não encontrado");
    }

    }

    private void updateData(User newOBj, User obj) {
        newOBj.setName(obj.getName());
        newOBj.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO objDTO){
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }
}
