package com.waelawada.learn.springboot.services;

import com.waelawada.learn.springboot.dao.ApartmentDao;
import com.waelawada.learn.springboot.domain.community.Apartment;
import com.waelawada.learn.springboot.services.GenericService;
import org.springframework.stereotype.Service;

/**
 * Created by waelawada on 2/21/15.
 */
@Service
public class ApartmentService extends GenericService<Apartment, ApartmentDao> {
}
