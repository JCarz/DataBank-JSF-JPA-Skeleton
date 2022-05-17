package databank.ejb;



import java.io.Serializable;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.log.Log;

import databank.model.PersonPojo;

@Singleton
public class PersonServiceEJB  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(name="PU_DataBank")
	protected EntityManager em;
	
	private static final Logger LOG = LogManager.getLogger();
	
	/**
	 * Default constructor.
	 */
	public PersonServiceEJB() {}
	
	
	public List<PersonPojo> findAllPersons(){
		TypedQuery<PersonPojo> allPeopleQuery = em.createNamedQuery(PersonPojo.PERSON_FIND_ALL,PersonPojo.class);
		return allPeopleQuery.getResultList();
	}

	@Transactional
	public PersonPojo persistPerson(PersonPojo person) {
		LOG.debug("creating a person = {}",person);
		em.persist(person);
		return person;
	}
	
	public PersonPojo findPersonById(int id) {
		LOG.debug("reading specific person = {}",id);
		return em.find(PersonPojo.class, id);
	}
	
	@Transactional
	public PersonPojo mergePerson(PersonPojo personWithUpdates) {
	LOG.debug("updating a person = {}",personWithUpdates);
	PersonPojo mergedPersonPojo = em.merge(personWithUpdates);
	return mergedPersonPojo;
	}
	
	@Transactional
	public void removePerson(int personId) {
		LOG.debug("deleting a specific person = {}",personId);
		PersonPojo person = findPersonById(personId);
		LOG.debug("deleting a specific person = {}",person);
		if(person != null) {
			em.refresh(person);
			em.remove(person);
		}
	}
}
