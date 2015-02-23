package com.waelawada.learn.springboot.dao;

import com.waelawada.learn.springboot.domain.community.Community;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by waelawada on 2/21/15.
 */
public interface CommunityDao extends CrudRepository<Community, Long> {
}
