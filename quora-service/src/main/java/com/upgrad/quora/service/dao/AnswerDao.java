package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.AnswerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AnswerDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * This method persists the given answer entity to the database
     *
     * @param answerEntity The answer details provided by user
     * @return AnswerEntity The persisted answer object
     */
    public AnswerEntity createAnswer(AnswerEntity answerEntity) {
        entityManager.persist(answerEntity);
        return answerEntity;
    }

    /**
     * This method fetches the answer entity from the database based on answer Uuid
     *
     * @param answerUuid The answerUuid provided by user
     * @return AnswerEntity The persisted answer object
     */
    public AnswerEntity getAnswerById(final String answerUuid) {
        try {
            return entityManager.createNamedQuery("getAnswerById", AnswerEntity.class).setParameter("answerUuid", answerUuid).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    /**
     * This method updates the answer entity in the database based on answer Uuid
     *
     * @param answerEntity The updated answerEntity provided by user
     * @return answerEntity The updated answer Entity
     */
    public AnswerEntity editAnswerByUuid(AnswerEntity answerEntity) {
        entityManager.merge(answerEntity);
        return answerEntity;
    }

    /**
     * This method deletes the answer entity from the database based on answer Uuid
     *
     * @param answerUuid The answerUuid provided by user
     * @return Integer The number of deleted answer Entities
     */
    public Integer deleteAnswerByUuid(final String answerUuid) {
        return entityManager.createQuery("delete from AnswerEntity a where a.uuid = :answerUuid").setParameter("answerUuid", answerUuid).executeUpdate();
    }

    /**
     * This method fetches all the answer entity in the database based on question Uuid
     *
     * @param questionId The questionUuid provided by user to fetch all the answers
     * @return answerEntity The list of answer Entity
     */
    public List<AnswerEntity> getAllAnswersToQuestion(final String questionId) {
        return entityManager.createNamedQuery("getAllAnswersToQuestion", AnswerEntity.class).setParameter("uuid", questionId).getResultList();
    }
}
