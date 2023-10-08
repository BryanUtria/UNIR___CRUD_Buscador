package com.bscu.crud.UsersBD.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bscu.crud.UsersBD.Entity.User;
import com.bscu.crud.UsersBD.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User save(User user) {
        System.out.println(user);
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public List<User> findByValor(String valor) {
        return (List<User>) userRepository.findByValor(valor);
    }

    public Boolean deleteById(String id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
