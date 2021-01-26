package pl.pawel.cqrs.persistence.aggregatestatistic;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PersonAggregateStatistics {

    private String name;
    private int avgSalary;
}
