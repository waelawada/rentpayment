package com.waelawada.learn.springboot.services;

import com.waelawada.learn.springboot.dao.UserDao;
import com.waelawada.learn.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by waelawada on 2/13/15.
 */
@Service
public class UserService extends GenericService<User, UserDao> {



}
