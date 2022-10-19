package com.prueba.restSprint.Controllers;

import com.prueba.restSprint.Dao.userDaoImplement;
import com.prueba.restSprint.Models.UserModel;
import com.prueba.restSprint.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private userDaoImplement userDao;

    @Autowired
    private JWTUtils jwtUtils;
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String verifyUsr(@RequestBody UserModel usuario){

        UserModel userLogin= userDao.userLogin(usuario);
       if ( userLogin != null) {

           String token = jwtUtils.create(String.valueOf(userLogin.getUserId()), userLogin.getEmail());
           return token;
       }
        return "FAIL";
    }
}
