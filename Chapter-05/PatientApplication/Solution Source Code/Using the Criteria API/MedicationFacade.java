package packt;


import java.io.PrintWriter;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MedicationFacade extends AbstractFacade<Medication> {

    @PersistenceContext(unitName="PatientApplication-ejbPU")
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public MedicationFacade() {
        super(Medication.class);
    }

    public List<String> findByType() {
        Query query = entityManager.createQuery("SELECT m.name FROM Medication m WHERE m.type = 'ACE'");
        List<String> list = query.getResultList();
        return list;
    }

    public int updateDosage(String type, int dosage) {
        Query query = entityManager.createQuery("UPDATE Medication m " +
                "SET m.dosage = " + dosage + " WHERE m.type = '" + type + "'");
        int numberUpdated = query.executeUpdate();
        return numberUpdated;
    }

    public List<Patient> findByLastName(String lastName) {
        Query query = em.createQuery("SELECT p FROM Patient p WHERE p.lastName = :lastName");
        query.setParameter("lastName", lastName);
        List<Patient> list = query.getResultList();
        return list;
    }

    public List<Medication> findByType(String type) {
        Query query = entityManager.createNamedQuery("findByType");
        query.setParameter(1,type);
        return query.getResultList();
    }

}
