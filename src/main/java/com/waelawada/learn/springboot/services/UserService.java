package com.waelawada.learn.springboot.services;

import com.waelawada.learn.springboot.dao.PaymentMethodDao;
import com.waelawada.learn.springboot.dao.UserDao;
import com.waelawada.learn.springboot.domain.User;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by waelawada on 2/13/15.
 */
@Service
public class UserService extends GenericService<User, UserDao> {

    @Autowired
    private PaymentMethodDao paymentMethodDao;

    public User updateUser(Long id, User user){
        User userToBeUpdated = dao.findOne(id);
        if(user.getAddress() != userToBeUpdated.getAddress()){
            userToBeUpdated.setAddress(user.getAddress());
        }
        if(!user.getEmail().equals(userToBeUpdated.getEmail())){
            userToBeUpdated.setEmail(user.getEmail());
        }
        if(!user.getFirstName().equals(userToBeUpdated.getFirstName())){
            userToBeUpdated.setFirstName(user.getFirstName());
        }
        if(!user.getLastName().equals(userToBeUpdated.getLastName())){
            userToBeUpdated.setLastName(user.getLastName());
        }
        if(!user.getPassword().equals(userToBeUpdated.getPassword())){
            userToBeUpdated.setPassword(user.getPassword());
        }
        return dao.save(userToBeUpdated);
    }

    public Boolean removePaymentMethodForUser(Long userId, Long paymentMethodId){
        User user = findById(userId);
        return user.getPaymentMethods().remove(paymentMethodDao.findOne(paymentMethodId));
    }

    public User addPaymentMethodToUser(User user, PaymentMethod paymentMethod){
        user.getPaymentMethods().add(paymentMethod);
        return save(user);
    }


}
