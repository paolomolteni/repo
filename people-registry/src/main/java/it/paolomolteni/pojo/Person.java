package it.paolomolteni.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
	
	/**
	 * 
	 */
	@Id
    @Column(name = "person_id", unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    /**
     * 
     */
    @Column(name = "name", nullable = false)
    private String name;
    
    /**
     * 
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    /**
     * 
     */
    @Column(name = "age", nullable = true)
    private Integer age;
    
    /**
     * 
     */
    @OneToMany(mappedBy="person", fetch = FetchType.LAZY)
    private List<Car> cars;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the cars
	 */
	public List<Car> getCars() {
		return cars;
	}

	/**
	 * @param cars the cars to set
	 */
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}
