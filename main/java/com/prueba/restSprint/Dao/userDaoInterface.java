package com.prueba.restSprint.Dao;

import com.prueba.restSprint.Models.UserModel;


import java.util.List;

public interface userDaoInterface {


    UserModel userLogin(UserModel usuario);

    public List<UserModel> getUsers();

    void delUser(Long id);

    void insertUser(UserModel usuario);


}
