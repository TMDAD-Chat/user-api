package es.unizar.tmdad.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "user")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "superuser")
    private Boolean isSuperUser;

}
