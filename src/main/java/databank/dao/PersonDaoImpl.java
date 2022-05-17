/*****************************************************************
 * File: PersonPojo.java Course materials (22W) CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import databank.ejb.PersonServiceEJB;
import databank.model.PersonPojo;

@Named
@ApplicationScoped
public class PersonDaoImpl implements PersonDao, Serializable {
	/** explicitly set serialVersionUID */
	private static final long serialVersionUID = 1L;

	//get the log4j2 logger for this class
	private static final Logger LOG = LogManager.getLogger();
	
	@EJB
	protected PersonServiceEJB service;
	
	@Override
	public List< PersonPojo> readAllPeople() {
		LOG.debug( "reading all people");
		//use the named JPQL query from the PersonPojo class to grab all the people
		 return service.findAllPersons();
		//execute the query and return the result/s.
//		return allPeopleQuery.getResultList();
	}

	@Override
	public PersonPojo createPerson( PersonPojo person) {
		LOG.debug( "creating a person = {}", person);
		service.persistPerson(person);
		return person;
	}

	@Override
	public PersonPojo readPersonById( int personId) {
		LOG.debug( "read a specific person = {}", personId);
		return service.findPersonById(personId);
	}

	@Override
	public PersonPojo updatePerson( PersonPojo personWithUpdates) {
		LOG.debug( "updating a specific person = {}", personWithUpdates);
		service.mergePerson(personWithUpdates);
		return personWithUpdates;
	}

	@Override
	public void deletePersonById( int personId) {
		LOG.debug( "deleting a specific personID = {}", personId);
		service.removePerson(personId);	
	}

}