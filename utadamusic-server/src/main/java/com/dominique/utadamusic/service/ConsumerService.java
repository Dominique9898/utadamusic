package com.dominique.utadamusic.service;


import com.dominique.utadamusic.domain.Consumer;

import java.util.List;

public interface ConsumerService {

    boolean addUser(Consumer consumer);

    boolean updateUserMsg(Consumer consumer);

    boolean updateUserAvator(Consumer consumer);

    boolean existUser(String username);

    boolean veritypasswd(String username, String password);

    boolean ifAdd(Consumer consumer);

    boolean deleteUser(Integer id);

    List<Consumer> allUser();

    List<Consumer> conmmentUser(Integer id);

    List<Consumer> consumerLists(String username);

}
