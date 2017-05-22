package models.supplier.entities;

import models.core.GenericEntity;

import javax.persistence.*;

/**
 * Created by LuisSebastian on 5/21/17.
 * Entity that represent a Supplier
 */
@Entity
public class Supplier extends GenericEntity {

    /**
     * Id entidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Supplier name
     */
    @Column
    public String name;

    /**
     * Latitude
     */
    @Column
    public String latitude;

    /**
     * Longitude
     */
    @Column
    public String longitude;

}
