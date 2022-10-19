package com.prueba.restSprint.Dao;

import com.prueba.restSprint.Models.UserModel;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class userDaoImplement implements userDaoInterface{


@PersistenceContext
    EntityManager entityManager;
    @Override
    public UserModel userLogin(UserModel usuario) {
        String query = "FROM UserModel WHERE email = :email";
        List<UserModel> respuesta = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if (respuesta.isEmpty())
            return null;

        String passHash = respuesta.get(0).getPassword();
        Argon2 haseador = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if( haseador.verify(passHash, usuario.getPassword()))
            return respuesta.get(0);

        return null;

    }


    @Override
    @Transactional
    public List<UserModel> getUsers() {
        String query = "FROM UserModel";
        return entityManager.createQuery(query).getResultList();


    }

    @Override
    public void delUser(Long id) {
        UserModel usuario = entityManager.find(UserModel.class, id);
        entityManager.remove(usuario);
    }
    @Override
    public void insertUser(UserModel usuario) {
        entityManager.merge(usuario);
    }
}
