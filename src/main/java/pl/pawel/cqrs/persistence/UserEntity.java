package pl.pawel.cqrs.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@Data
@Entity(name = "User")
@NoArgsConstructor
public class UserEntity {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    private String name;

    private String organization;

    private String surname;
}
