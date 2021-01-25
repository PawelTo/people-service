package pl.pawel.cqrs.persistence;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditorAwareImpl implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        return Optional.of(123);
    }
}
