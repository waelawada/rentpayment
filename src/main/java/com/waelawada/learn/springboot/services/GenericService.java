package com.waelawada.learn.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by waelawada on 2/13/15.
 */
public abstract class GenericService<Entity extends java.lang.Object, Dao extends CrudRepository> {

    @Autowired
    Dao dao;

    public Entity save(Entity entity){
        return (Entity) dao.save(entity);
    }

    public void delete(Long id){
        dao.delete(id);
    }

    public Long getCount(){
        return dao.count();
    }

    public Entity findById(Long id){
        return (Entity) dao.findOne(id);
    }
}
