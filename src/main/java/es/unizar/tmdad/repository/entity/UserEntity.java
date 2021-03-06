package es.unizar.tmdad.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "photo", nullable = false)
    private String photoUri;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "superuser")
    private Boolean isSuperUser;


    @ManyToMany(mappedBy = "users")
    @ToString.Exclude
    private Set<RoomEntity> rooms = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return name != null && Objects.equals(name, that.name);
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_conversation",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "second_user_id") }
    )
    @ToString.Exclude
    Set<UserEntity> contacts = new HashSet<>();
}
