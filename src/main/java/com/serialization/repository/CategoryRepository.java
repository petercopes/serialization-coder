package com.serialization.repository;

import com.serialization.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Role, Long>  {

}
