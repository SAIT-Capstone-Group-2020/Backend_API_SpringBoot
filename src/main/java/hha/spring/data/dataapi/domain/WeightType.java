package hha.spring.data.dataapi.domain;

import javax.persistence.*;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 * <p>
 * weight_type table
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Entity
@Table(name = "weight_type")
public class WeightType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weight_type_id")
    private int id;
    @Column(name = "weight_type_name")
    private String name;

    /**
     * Instantiates a new Weight type.
     */
    public WeightType() {
    }

    /**
     * Instantiates a new Weight type.
     *
     * @param name the name
     */
    public WeightType(String name) {
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
