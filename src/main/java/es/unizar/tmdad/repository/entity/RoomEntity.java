package es.unizar.tmdad.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "room")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_generator")
    @SequenceGenerator(name = "room_id_generator", sequenceName = "room_id_sequence", allocationSize = 1)
    @Column(name = "name", nullable = false)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_name", nullable = false)
    @ToString.Exclude
    private UserEntity owner;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "room_users",
            joinColumns = { @JoinColumn(name = "room_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    @ToString.Exclude
    Set<UserEntity> users = new HashSet<>();

}
