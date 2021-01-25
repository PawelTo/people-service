package pl.pawel.cqrs.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@AllArgsConstructor
@Audited(targetAuditMode = NOT_AUDITED, withModifiedFlag = true)
@AuditTable(value = "person_aud")
@Builder(toBuilder = true)
@Data
@Entity(name = "person")
@NoArgsConstructor
public class PersonEntity extends Auditable<Integer> {

    @EmbeddedId
    private PersonKey personKey;
    private String address;
    private String name;
    private int salary;

    @AllArgsConstructor
    @Builder
    @Data
    @Embeddable
    @NoArgsConstructor
    public static class PersonKey implements Serializable {
        private LocalDate dateOfBirth;
        private String surname;
    }
}
