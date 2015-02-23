package com.waelawada.learn.springboot.dao;

import com.waelawada.learn.springboot.domain.users.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by waelawada on 1/16/15.
 */
public interface UserDao extends CrudRepository<User, Long> {
}
