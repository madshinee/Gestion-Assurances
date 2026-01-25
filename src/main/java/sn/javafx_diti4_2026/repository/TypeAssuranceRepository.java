package sn.javafx_diti4_2026.repository;

import sn.javafx_diti4_2026.Entity.TypeAssurance;
import sn.javafx_diti4_2026.repository.interfaceRepo.IInterface;
import sn.javafx_diti4_2026.utils.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class TypeAssuranceRepository implements IInterface<TypeAssurance> {

    private EntityManager entityManager;

    public TypeAssuranceRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    @Override
    public void insert(TypeAssurance typeAssurance) {
        entityManager.getTransaction().begin();
        entityManager.persist(typeAssurance);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(TypeAssurance typeAssurance) {
        entityManager.getTransaction().begin();
        entityManager.merge(typeAssurance);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        TypeAssurance typeAssurance = entityManager.find(TypeAssurance.class, id);
        if (typeAssurance != null) {
            entityManager.remove(typeAssurance);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public TypeAssurance findById(int id) {
        return entityManager.find(TypeAssurance.class, id);
    }

    @Override
    public List<TypeAssurance> findAll() {
        List<TypeAssurance> typeAssurances;
        typeAssurances = entityManager.createQuery("SELECT ta FROM TypeAssurance ta", TypeAssurance.class).getResultList();
        return typeAssurances;
    }
}
