package com.dominique.utadamusic.service.impl;


import com.dominique.utadamusic.dao.ConsumerMapper;
import com.dominique.utadamusic.domain.Consumer;
import com.dominique.utadamusic.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public boolean addUser(Consumer consumer) {
        return consumerMapper.addUser(consumer) >0 ?true:false;
    }

    @Override
    public boolean updateUserMsg(Consumer consumer) {
        return consumerMapper.updateUserMsg(consumer) >0 ?true:false;
    }

    @Override
    public boolean updateUserAvator(Consumer consumer) {

        return consumerMapper.updateUserAvator(consumer) >0 ?true:false;
    }

    @Override
    public boolean existUser(String username) {
        return consumerMapper.existUsername(username)>0 ? true:false;
    }

    @Override
    public boolean veritypasswd(String username, String password) {

        return consumerMapper.verifyPassword(username, password) > 0;
    }

//    删除用户
    @Override
    public boolean deleteUser(Integer id) {
        return consumerMapper.deleteUser(id) > 0;
    }

    @Override
    public List<Consumer> allUser()
    {
        return consumerMapper.allUser();
    }

    @Override
    public boolean ifAdd(Consumer consumer)
    {

        return consumerMapper.addUser(consumer) > 0;
    }

    @Override
    public List<Consumer> conmmentUser(Integer id) {

        return consumerMapper.conmmentUser(id);
    }

    @Override
    public List<Consumer> consumerLists(String username)
    {

        return consumerMapper.consumerLists(username);
    }
}
