package com.serialization.service;

import com.serialization.model.User;
import com.serialization.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ObjectMapper mapper;

    @PostConstruct
    private void PostConstruct() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
    }

    @Override
    public User createUser(User user) {
        try {
            mapperToString(user);
            mapperToMap(user);
            mapperToClass(user);

            return repository.save(user);
        } catch (JsonProcessingException e) {
            log.error("Error convertiong message to string", e);
        }
        return user;
    }

    void mapperToString(User user) throws JsonProcessingException {
        var userString = mapper.writeValueAsString(user);
        log.info("Mensaje en formato String : {}", userString);
    }
    void mapperToMap(User user) throws JsonProcessingException {
        var userString = mapper.writeValueAsString(user);
        var userMap = mapper.readValue(userString, Map.class);
        log.info("Mensaje en formato de Mapa : {}", userMap);
    }

    void mapperToClass(User user) throws JsonProcessingException {
        var userString = mapper.writeValueAsString(user);
        var userClass = mapper.readValue(userString, User.class);
        log.info("Mensaje en formato de Clase : {}", userClass);
    }


    @Override
    public User getUserById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public User updateUserById(User user, Long id) {
        user.setId(id);
        return repository.save(user);
    }

    @Override
    public void delete(User user, Long id) {
        user.setId(id);
        repository.delete(user);
    }

    @Override
    public User findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<User> findByAgeGreaterThan(int age) {
        return repository.findByAgeGreaterThan(age);
    }

    @Override
    public List<User> findAll() {
        var userList =  new ArrayList<User>();
        repository.findAll().forEach(userList::add);
        return userList;
    }
}
