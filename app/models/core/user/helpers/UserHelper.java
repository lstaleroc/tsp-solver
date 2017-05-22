package models.core.user.helpers;

import models.core.user.entities.User;
import play.Logger;
import play.db.jpa.JPA;

import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 * Created by LuisSebastian on 5/21/17.
 */
@Singleton
public class UserHelper {

    /**
     * Method that query for an user that match with username - password.
     *
     * @param email user email
     * @param password user password
     * @return The user if exists, else null.
     */
    public User authenticateUser(String email, String password) {

        User user;
        Query query = JPA.em().createNamedQuery("User.authenticate").setParameter("email", email).setParameter("password", password);
        try {
            user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException nre) {
            Logger.info("No hay un usuario con el login y el password buscado: ", nre);
            return null;
        } catch (NonUniqueResultException nure) {
            Logger.info("Hay más de un usuario con el login y el password buscado: ", nure);
            user = (User) query.getResultList().get(0);
            return user;
        }
    }

}
