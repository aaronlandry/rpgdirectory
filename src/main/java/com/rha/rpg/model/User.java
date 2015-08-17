package com.rha.rpg.model;

import com.rha.rpg.persistence.UserRepository;
import com.rha.rpg.util.Hasher;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Mutable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Parent class for User model entities.
 *
 * @author Aaron
 */
// Persistence
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Users")
public class User extends AbstractPersistable<User, UserRepository> {

    // INJECTION
    @Autowired(required=true)
    private transient UserRepository userRepositoryImpl;
    // PERSISTED ATTRIBUTES
    private String username;
    private String firstName;
    private String lastName;
    private String middleName;
    private Role role = Role.STANDARD;
    private String password = "JOOKIE";

    @Override
    // PERSISTENCE
    @Id
    @SequenceGenerator(name = "USER_ID_GENERATOR", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_GENERATOR")
    @Mutable(false)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    // VALIDATION
    @PropertyAlias("User Name")
    @NotNull(message="User Name is required", groups = Default.class )
    @Size(max = 30, message = "User Name cannot exceed 30 characters", groups = Default.class)
    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // PERSISTENCE
    @PropertyAlias("First Name")
    @NotNull(message="First Name is required", groups = Default.class )
    @Size(max = 100, message = "First Name cannot exceed 100 characters", groups = Default.class)
    @Column(name = "firstName", nullable = false, length = 100)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // PERSISTENCE
    @PropertyAlias("Middle Name")
    @NotNull(message="Middle Name is required", groups = Default.class )
    @Size(max = 50, message = "Middle Name cannot exceed 50 characters", groups = Default.class)
    @Column(name = "middleName", length = 50)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    // PERSISTENCE
    @PropertyAlias("Last Name")
    @NotNull(message="Last Name is required", groups = Default.class )
    @Size(max = 100, message = "Last Name cannot exceed 100 characters", groups = Default.class)
    @Column(name = "lastName", nullable = false, length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // PERSISTENCE
    @NotNull(message="Password is required", groups = Default.class )
    @Size(max = 255, message = "Password cannot exceed 255 characters", groups = Default.class)
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PropertyAlias("Role")
    @Convert("persistableEnum")
    @NotNull(message="Role is required", groups = Default.class)
    @Column(name = "roleID", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    /* Override Object Methods */
    @Override
    public String toString() {
        if (getLastName() == null) {
            return getFirstName();
        }
        return getFirstName() + " " + getLastName();
    }

    @Override
    public String getSortableString() {
        return getLastName() + ", " + getFirstName();
    }

    // Maintain contract between hashcode and equals
    @Override
    public int hashCode() {
        return new Hasher().hash(getUsername()).getValue();
    }

    @Override
    public boolean equals(Object b) {
        if (b == null) {
            return false;
        }
        if (this == b) {
            return true;
        }
        if (!User.class.isInstance(b)) {
            return false;
        }
        final User B = (User) b;
        if (getUsername() == null) {
            return false;
        }
        return getUsername().equals(B.getUsername());
    }

    /* Implement Comparable */
    @Override
    public int compareTo(User B) {
        if (B == null) {
            return -1;
        }
        if (!getLastName().equals(B.getLastName())) {
            return getLastName().compareTo(B.getLastName());
        }
        if (!getFirstName().equals(B.getFirstName())) {
            return getFirstName().compareTo(B.getFirstName());
        }
        if (getMiddleName() != null) {
            if (B.getMiddleName() == null) {
                return 1;
            } 
            else if (!getMiddleName().equals(B.getMiddleName())) {
                return getMiddleName().compareTo(B.getMiddleName());
            }
        }
        return getUsername().compareTo(B.getUsername());
    }

    @Override
    public UserRepository getRepository() {
        return userRepositoryImpl;
    }
    
    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> rtn = new HashMap<>();
        rtn.put("id",getId());
        rtn.put("firstName",getFirstName());
        rtn.put("lastName",getLastName());
        rtn.put("userName",getUsername());
        return rtn;
    }

}
