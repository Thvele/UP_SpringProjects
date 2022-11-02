package com.example.P50519.Repositories;

import com.example.P50519.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String l);
}
