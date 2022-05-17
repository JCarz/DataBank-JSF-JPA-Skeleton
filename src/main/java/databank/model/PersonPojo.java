/*****************************************************************
 * File: PersonPojo.java Course materials (22W) CST8277
 *
 * @author Teddy Yap
 * @author Shahriar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity(name = "DatabankPerson")
@Table(name = "Person", catalog = "databank")
@Access(AccessType.FIELD)
/**Field is used to identify each and every variable */
@EntityListeners({ PersonPojoListener.class })
@NamedQuery(name = PersonPojo.PERSON_FIND_ALL, query = "SELECT a FROM DatabankPerson a")
@NamedQuery(name = PersonPojo.PERSON_FIND_ID, query = "SELECT a FROM DatabankPerson a WHERE a.id = :id")

//@NamedQuery(name = PersonPojo.PERSON_CREATE_PERSON, query ="");

public class PersonPojo implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String PERSON_FIND_ALL = "Person.findAll";
	public static final String PERSON_FIND_ID = "Person.findById";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",columnDefinition = "int")
	private int id;
	
	@Column(name = "last_name",columnDefinition = "VARCHAR")
	private String lastName;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "email",columnDefinition = "VARCHAR")
	private String email;
	
	@Column(name = "phone")
	private String phoneNumber;
	
	@Column(name = "city",columnDefinition = "VARCHAR")
	private String city;

	private LocalDateTime updated;

	private LocalDateTime created;

	@Column(name = "version")
	@Version
	private int version = 1;
	
	@Transient
	private boolean editable;

	public PersonPojo() {
		super();
	}

	public boolean getEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public int getId() {
		return id;
	}

	/**
	 * @param id new value for id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the value for lastName
	 */
	
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName new value for lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the value for firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName new value for firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
//	 add the city field, add getter and setter, and update toString

	/**
	 * Very important: use getter's for member variables because JPA sometimes needs
	 * to intercept those calls<br/>
	 * and go to the database to retrieve the value
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		// only include member variables that really contribute to an object's identity
		// i.e. if variables like version/updated/name/etc. change throughout an
		// object's lifecycle,
		// they shouldn't be part of the hashCode calculation
		return prime * result + Objects.hash(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		/*
		 * enhanced instanceof - yeah! As of JDK 14, no need for additional 'silly'
		 * cast: if (animal instanceof Cat) { Cat cat = (Cat)animal; cat.meow(); //
		 * other class Cat operations ... } Technically, 'otherPersonPojo' is a
		 * <i>pattern</i> that becomes an in-scope variable binding. Note: need to watch
		 * out just-in-case there is already a 'otherPersonPojo' variable in-scope!
		 */
		if (obj instanceof PersonPojo otherPersonPojo) {
			// see comment (above) in hashCode(): compare using only member variables that
			// are
			// truely part of an object's identity
			return Objects.equals(this.getId(), otherPersonPojo.getId());
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [id=").append(getId());
		if (getFirstName() != null) {
			builder.append(", ").append("firstName=").append(getFirstName());
		}
		if (getLastName() != null) {
			builder.append(", ").append("lastName=").append(getLastName());
		}
		if (getPhoneNumber() != null) {
			builder.append(", ").append("phoneNumber=").append(getPhoneNumber());
		}
		if (getCity() != null) {
			builder.append(",").append("city=").append(getCity());
		}
		if (getEmail() != null) {
			builder.append(", ").append("email=").append(getEmail());
		}
		if (getCreated() != null) {
			builder.append(", ").append("created=").append(getCreated());
		}
		if (getUpdated() != null) {
			builder.append(", ").append("updated=").append(getUpdated());
		}
		builder.append("]");
		return builder.toString();
	}

}