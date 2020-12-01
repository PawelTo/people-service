package pl.pawel.cqrs.persistence.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import pl.pawel.cqrs.controllers.serializer.DataTimeDeserializer;
import pl.pawel.cqrs.controllers.serializer.DataTimeSerializer;
import pl.pawel.cqrs.domain.ItemCategory;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@AllArgsConstructor
//@Audited(targetAuditMode = NOT_AUDITED, withModifiedFlag = true)
//@AuditTable(value = "item_aud")
@Builder(toBuilder = true)
@Data
@Entity(name = "item")
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    @JsonDeserialize(using = DataTimeDeserializer.class)
    @JsonSerialize(using = DataTimeSerializer.class)
    private LocalDateTime creationTimestamp;
    private String description;
    private String name;
}
