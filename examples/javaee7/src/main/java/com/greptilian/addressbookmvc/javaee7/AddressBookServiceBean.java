package com.greptilian.addressbookmvc.javaee7;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@SuppressWarnings({"initialization.fields.uninitialized", "argument.type.incompatible"})
@Stateless
@Named
public class AddressBookServiceBean {

    private static final Logger logger = Logger.getLogger(AddressBookServiceBean.class.getCanonicalName());

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("return.type.incompatible")
    public Person find(long id) {
        TypedQuery<Person> typedQuery = em.createQuery("SELECT OBJECT(o) FROM Person AS o WHERE o.id = :id", Person.class);
        typedQuery.setParameter("id", id);
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            return null;
        }
    }

    public List<Person> findAll() {
        TypedQuery<Person> typedQuery = em.createQuery("SELECT OBJECT(o) FROM Person AS o", Person.class);
        return typedQuery.getResultList();
    }

    @SuppressWarnings("return.type.incompatible")
    public Person add(Person toPersist) {
        Person persisted = null;
        try {
            persisted = em.merge(toPersist);
        } catch (Exception ex) {
            logger.info("Problem persisting person: " + ex);
        }
        return persisted;
    }

    public Person save(Person personToSave) {
        return em.merge(personToSave);
    }

    public boolean delete(long id) {
        Person doomed = find(id);
        boolean wasDeleted = false;
        if (doomed != null) {
            em.remove(doomed);
            em.flush();
            wasDeleted = true;
        }
        return wasDeleted;
    }

}
