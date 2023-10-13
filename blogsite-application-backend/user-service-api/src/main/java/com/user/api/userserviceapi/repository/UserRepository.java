package com.user.api.userserviceapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.user.api.userserviceapi.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
