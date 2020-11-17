package pl.pawel.cqrs.persistence.entity;

import lombok.Data;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Audited(targetAuditMode = NOT_AUDITED, withModifiedFlag = true)
@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class Auditable<U> {

    public static final String STATUS_NOT_DELETED_CLAUSE = "last_operation <> 'DELETE'";

    @CreatedBy
    protected U createdBy;
    @CreatedDate
    protected LocalDateTime createdAt;
    @LastModifiedBy
    protected U lastModifiedBy;
    @LastModifiedDate
    protected LocalDateTime lastModifiedAt;
}


