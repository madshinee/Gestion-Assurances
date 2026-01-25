package sn.javafx_diti4_2026.repository;

import sn.javafx_diti4_2026.Entity.Assurance;
import sn.javafx_diti4_2026.repository.interfaceRepo.IInterface;
import sn.javafx_diti4_2026.utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AssuranceRepository implements IInterface<Assurance> {

    private EntityManager entityManager;

    public AssuranceRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public void insert(Assurance assurance) {
        entityManager.getTransaction().begin();
        entityManager.persist(assurance);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Assurance assurance) {
        entityManager.getTransaction().begin();
        entityManager.merge(assurance);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        Assurance assurance = entityManager.find(Assurance.class, id);
        entityManager.remove(assurance);
        entityManager.getTransaction().commit();
    }

    @Override
    public Assurance findById(int id) {
        return entityManager.find(Assurance.class, id);
    }

    @Override
    public List<Assurance> findAll() {
        List<Assurance> assurances;
        assurances = entityManager.createQuery("SELECT a FROM Assurance a", Assurance.class).getResultList();
        return assurances;
    }

    public List<Assurance> search(String keyword) {
        TypedQuery<Assurance> query = entityManager.createQuery(
                "SELECT a FROM Assurance a WHERE a.nomClient LIKE :keyword OR a.numero LIKE :keyword", Assurance.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

}
