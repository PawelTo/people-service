package pl.pawel.cqrs.persistence.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pawel.cqrs.controllers.serializer.DateTimeDeserializer;
import pl.pawel.cqrs.controllers.serializer.DateTimeSerializer;
import pl.pawel.cqrs.domain.ItemCategory;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

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

    @JsonDeserialize(using = DateTimeDeserializer.class)
    @JsonSerialize(using = DateTimeSerializer.class)
    private LocalDateTime creationTimestamp;
    private String description;
    private String name;
}
