package models.core;

import play.Logger;
import play.db.jpa.JPA;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by LuisSebastian on 5/22/17.
 * Represents a generic entity.
 */

public abstract class GenericEntity {


    /**
     * Persists the current instance into the database
     */
    public void save() {

        JPA.em().persist(this);
    }


    /**
     * Removes the current instance from the database
     */
    public void remove() {
        JPA.em().remove(this);
    }

    /**
     * Update record into the database
     */
    public void update() {
        JPA.em().merge(this);
    }


    /**
     * Given the class from an entity, it returns all the records of that entity
     *
     * @param pClass Class of the entity that is being looked up
     * @return A collection of objects of type pClass
     */
    @SuppressWarnings("rawtypes")
    public static List findAll(Class pClass) {
        return JPA.em().createQuery("from " + pClass.getName()).getResultList();
    }


    /**
     * Locates an entity given its id and its class
     *
     * @param id Id of the entity
     * @param pClass Class of the entity
     * @return An object representing the entity with the specified id or null
     * if the entity was not found
     */
    public static Object findById(Long id,
                                  @SuppressWarnings("rawtypes")
                                          Class pClass) {
        Query query = JPA.em().createQuery("from " + pClass.getName() + " where id = " + id);
        try {
            return query.getSingleResult();
        } catch (NonUniqueResultException nure) {
            Logger.error("findById id - >"+id, nure);
            return query.getResultList().get(0);
        } catch (NoResultException nre) {
            Logger.error("findById id - >"+id, nre);
            return null;
        }
    }
}
