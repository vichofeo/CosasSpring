package com.prueba.restSprint.Controllers;

import com.prueba.restSprint.Dao.userDaoImplement;
import com.prueba.restSprint.Models.UserModel;
import com.prueba.restSprint.utils.JWTUtils;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private userDaoImplement userDao;

    @Autowired
    private JWTUtils jwtUtils;

    // verifica token
    private boolean validateToken(String token){
        String usr = jwtUtils.getKey(token);
        return usr!=null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<UserModel> getUsers(@RequestHeader(value = "Authorization") String token){


if (!validateToken(token))
    return null;
        return userDao.getUsers();
    }

    //eliminacion
    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void delUser(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        if (!validateToken(token))           return;
                    userDao.delUser(id);
    }
//insert
    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void insertUser(@RequestBody UserModel usuario){
        //hash pasword
        Argon2 argonHash = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String passHaseado = argonHash.hash(1,1024, 1, usuario.getPassword());
        usuario.setPassword(passHaseado);
        userDao.insertUser(usuario);
    }
}
