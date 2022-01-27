package com.serialization.repository;

import com.serialization.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>  {

    User findByName(String name);
    List<User> findByAgeGreaterThan(int age);
    List<User> findByAgeOrderByNameDesc(int age);

    @Modifying
    @Query("update User u set u.age = :age where u.name = :name")
    void updateAge(@Param(value = "age" ) int age, @Param(value = "name") String name);
}
