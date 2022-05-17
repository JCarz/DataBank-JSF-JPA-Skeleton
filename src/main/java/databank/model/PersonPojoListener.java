/*****************************************************************
 * File: PersonPojoListener.java Course materials (22W) CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.model;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class PersonPojoListener {

	@PrePersist
	public void setCreatedOnDate( PersonPojo person) {
		LocalDateTime now = LocalDateTime.now();
		person.setCreated( now);
		//might as well call setUpdated as well
		person.setUpdated( now);
	}


	@PreUpdate
	public void setUpdatedDate( PersonPojo person) {
		person.setUpdated( LocalDateTime.now());
	}

}