package com.educandoweb.course.servicer;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.exceptions.DatabaseExeption;
import com.educandoweb.course.exceptions.ResourceNotFoundExeption;
import com.educandoweb.course.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAll(){
        return  userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundExeption(id));
    }

    public User insert(User obj){
        return userRepository.save(obj);
    }

    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundExeption(id);
        }catch (RuntimeException e){
            throw new DatabaseExeption(e.getMessage());
        }
    }

    public User updateUser(Long id, User obj){
        try{
        User user = userRepository.getReferenceById(id);
        updateData(user,obj);
        return userRepository.save(user);
    }catch (EntityNotFoundException e){
           // e.printStackTrace();
            throw new ResourceNotFoundExeption(id);
        }
    }


    private void updateData(User user, User obj) {
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        user.setPhone(obj.getPhone());
    }
}
