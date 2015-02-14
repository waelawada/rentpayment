package com.waelawada.learn.springboot.dao;

import com.waelawada.learn.springboot.domain.Address;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by waelawada on 2/9/15.
 */
public interface AddressDao extends CrudRepository<Address, Long> {
}
