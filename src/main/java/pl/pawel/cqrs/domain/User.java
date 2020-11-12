package pl.pawel.cqrs.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Builder(toBuilder = true)
@Data
@JsonInclude(NON_EMPTY)
public class User {

    private long id;
    private String name;
    private User.Organization organization;
    private String surname;

    public enum Organization {
        O1,
        O2,
        O3;
    }
}
