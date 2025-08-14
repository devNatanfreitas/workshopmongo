package com.natanfreitas.workshopmongo.services;

import com.natanfreitas.workshopmongo.domain.Post;
import com.natanfreitas.workshopmongo.domain.User;
import com.natanfreitas.workshopmongo.dto.UserDTO;
import com.natanfreitas.workshopmongo.repository.PostRepository;
import com.natanfreitas.workshopmongo.repository.UserRepository;
import com.natanfreitas.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;


    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj
                .orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text){
        return repo.findByTitle(text);
    }
    public List<Post> fullSearch(String txt, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 *60 *60 *1000);
        return repo.fullSearch(txt, minDate, maxDate);
    }
}
