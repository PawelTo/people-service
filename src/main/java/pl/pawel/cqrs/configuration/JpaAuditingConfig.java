package pl.pawel.cqrs.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@RequiredArgsConstructor
public class JpaAuditingConfig {

    private final AuditorAware auditorAware;

    @Bean
    public AuditorAware<Integer> auditorAware(){
        return auditorAware;
    }
}
