package pl.pawel.cqrs.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@Data
@Entity(name = "Users")
@NoArgsConstructor
public class UserEntity {

    //@GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    private String name;

    private String organization;

    private String surname;
}
