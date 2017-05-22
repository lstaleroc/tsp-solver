package models.core.user.entities;

import javax.persistence.*;

/**
 * Created by LuisSebastian on 5/21/17.
 * Entity that represents an User
 */
@Entity
@NamedQueries({@NamedQuery(name = "User.authenticate", query = "select u from User u where email = :email and password = :password"),
        @NamedQuery(name = "User.findByLogin", query = "select u from User u where email = :email")})
public class User {

    /**
     * Id entidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User email - login
     */
    @Column
    public String email;

    /**
     * User password
     */
    @Column
    public String password;

    /**
     * User name
     */
    @Column
    public String name;

    /**
     * User last name
     */
    @Column
    public String lastName;

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }
}
