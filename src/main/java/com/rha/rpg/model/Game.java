package com.rha.rpg.model;

import com.rha.rpg.persistence.BooleanConverter;
import com.rha.rpg.persistence.PersistableEnumConverter;
import com.rha.rpg.persistence.GameRepository;
import com.rha.rpg.util.Hasher;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converters;
import org.eclipse.persistence.annotations.Converter;
import org.eclipse.persistence.annotations.Mutable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A game to be tracked in the directory.
 * @author Aaron
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Games")
@Converters({
    @Converter(name="persistableEnum", converterClass=PersistableEnumConverter.class),
    @Converter(name="boolean", converterClass=BooleanConverter.class)
})
public class Game extends AbstractPersistable<Game, GameRepository> {
    
    // INJECTION
    @Autowired(required=true)
    private transient GameRepository gameRepositoryImpl;
    private String name;
    private Category category;
    private User creator;
    private Boolean matchLowercase = Boolean.FALSE;

    @Override
    public GameRepository getRepository() {
        return gameRepositoryImpl;
    }
    
    @Override
    // PERSISTENCE
    @Id
    @SequenceGenerator(name = "GAME_ID_GENERATOR", sequenceName = "game_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GAME_ID_GENERATOR")
    @Mutable(false)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @PropertyAlias("Name")
    // VALIDATION
    @NotNull(message="Name is required", groups = Default.class )
    @Size(max = 100, message = "Name cannot exceed 100 characters", groups = Default.class)
    // PERSISTENCE
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    
    @PropertyAlias("Created By")
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @NotNull(message="Created By is required", groups = Default.class)
    @JoinColumn(name = "userID")
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
    
    @PropertyAlias("Category")
    @Convert("persistableEnum")
    @NotNull(message="Category is required", groups = Default.class)
    @Column(name = "categoryID", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    @Override
    public int compareTo(Game B) {
        return getName().compareTo(B.getName());
    }
    
    // Maintain contract between hashcode and equals
    @Override
    public int hashCode() {
        return new Hasher().hash(getName()).hash(getId()).getValue();
    }
    
    @Override
    public boolean equals(Object b) {
        if (b == null) {
            return false;
        }
        if (this == b) {
            return true;
        }
        if (!Game.class.isInstance(b)) {
            return false;
        }
        final Game B = (Game) b;
        if (getId() == null || B.getId() == null) {
            return getName().equals(B.getName());
        }
        return getId().equals(B.getId());
    }
    
    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> rtn = new HashMap<>();
        rtn.put("id",getId());
        rtn.put("name",getName());
        rtn.put("category",getCategory());
        rtn.put("creationDate",getCreationDate());
        rtn.put("creator",getCreator().toMap());
        return rtn;
    }
    
}
