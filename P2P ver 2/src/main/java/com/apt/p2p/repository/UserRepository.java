package com.apt.p2p.repository;

import com.apt.p2p.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {}
