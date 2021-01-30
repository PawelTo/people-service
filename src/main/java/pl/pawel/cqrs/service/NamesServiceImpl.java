package pl.pawel.cqrs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * Service which is used for learning PersistenceContext, Transactional, Resource and Lazy annotations
 */
@RequiredArgsConstructor
@Service
public class NamesServiceImpl implements NamesService {

    private List<String> names = new LinkedList<>();

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<String> getAll() {
        return createNames();
    }

    private List<String> createNames() {
        names.add("Imie utworzone o: " + ofPattern("YYYY-MM-DD HH:mm:ss")
                                                          .format(now()));
        return names;
    }

    @Transactional
    public void changeName(String newName, String oldName){
        entityManager.createQuery("UPDATE person SET name = :newName WHERE name = :oldName")
                .setParameter("newName", newName)
                .setParameter("oldName", oldName)
                .executeUpdate();

    }

    private void dummyMethodToTestPredicate(){
        Predicate<String> isShorterThan2 = (word) -> word.length()<2;
    }
}
