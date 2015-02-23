package com.waelawada.learn.springboot.services;

import com.waelawada.learn.springboot.dao.PaymentMethodDao;
import com.waelawada.learn.springboot.dao.UserDao;
import com.waelawada.learn.springboot.domain.users.ResidentUser;
import com.waelawada.learn.springboot.domain.users.User;
import com.waelawada.learn.springboot.domain.billing.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by waelawada on 2/13/15.
 */
@Service
public class UserService extends GenericService<User, UserDao> {

    @Autowired
    private PaymentMethodDao paymentMethodDao;

    public User updateUser(User userToBeUpdated, User user){
        if(user.getAddress() !=null && user.getAddress() != userToBeUpdated.getAddress()){
            userToBeUpdated.setAddress(user.getAddress());
        }
        if(user.getEmail() !=null && !user.getEmail().equals(userToBeUpdated.getEmail())){
            userToBeUpdated.setEmail(user.getEmail());
        }
        if(user.getFirstName() !=null && !user.getFirstName().equals(userToBeUpdated.getFirstName())){
            userToBeUpdated.setFirstName(user.getFirstName());
        }
        if(user.getLastName() !=null && !user.getLastName().equals(userToBeUpdated.getLastName())){
            userToBeUpdated.setLastName(user.getLastName());
        }
        if(user.getPassword() !=null && !user.getPassword().equals(userToBeUpdated.getPassword())){
            userToBeUpdated.setPassword(user.getPassword());
        }
//        if(user.getPaymentMethods() !=null && user.getPaymentMethods() != userToBeUpdated.getPaymentMethods()){
//            userToBeUpdated.setPaymentMethods(user.getPaymentMethods());
//        }
        return dao.save(userToBeUpdated);
    }

    public Boolean removePaymentMethodForUser(Long userId, Long paymentMethodId){
        ResidentUser user = (ResidentUser)findById(userId);
        return user.getPaymentMethods().remove(paymentMethodDao.findOne(paymentMethodId));
    }

    public User addPaymentMethodToUser(ResidentUser user, PaymentMethod paymentMethod){
        List<PaymentMethod> paymentMethods = user.getPaymentMethods();
        paymentMethods.add(paymentMethod);
        user.setPaymentMethods(paymentMethods);
        return save(user);
    }


}
