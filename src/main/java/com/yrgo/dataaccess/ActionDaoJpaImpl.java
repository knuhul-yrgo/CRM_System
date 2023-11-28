package com.yrgo.dataaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.yrgo.domain.Action;

@Repository
@Transactional
public class ActionDaoJpaImpl implements ActionDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Action newAction) {
        System.out.println("using jpa");
        em.persist(newAction);
    }

    public List<Action> getIncompleteActions(String userId) {
        return em
                .createQuery(
                        "SELECT action FROM Action AS a WHERE action.owning_user = :userId AND action.complete = false",
                        Action.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public void update(Action actionToUpdate) throws RecordNotFoundException {
        if (em.find(Action.class, actionToUpdate.getActionId()) != null) {
            em.merge(actionToUpdate);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void delete(Action oldAction) throws RecordNotFoundException {
        Action actionToDelete = em.find(Action.class, oldAction.getActionId());
        if (actionToDelete != null) {
            em.remove(actionToDelete);
        } else {
            throw new RecordNotFoundException();
        }
    }

}
