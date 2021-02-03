package pl.pawel.cqrs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawel.cqrs.domain.ItemCategory;
import pl.pawel.cqrs.persistence.entity.ItemEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static pl.pawel.cqrs.domain.ItemCategory.TECHNOLOGY;

/**
 * Service which is used for learning PersistenceContext, Transactional, Resource and Lazy
 * annotations
 */
@RequiredArgsConstructor
@Service
public class NamesServiceImpl implements NamesService {

  private List<String> names = new LinkedList<>();

  @PersistenceContext
  private final EntityManager entityManager;
  private final ItemService itemService;

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
  public int changeName(String newName, String oldName) {
    return entityManager.createQuery("UPDATE item SET name = :newName WHERE name = :oldName")
        .setParameter("newName", newName)
        .setParameter("oldName", oldName)
        .executeUpdate();
  }

  @Transactional
  public int create(String description, int id, String name) {
    return entityManager.createNativeQuery(
        "INSERT INTO {h-schema}item ( description, name) VALUES ( :description, :name)")
        .setParameter("description", description)
        //.setParameter("id", id)
        .setParameter("name", name)
        .executeUpdate();
  }

  @Transactional
  public ItemEntity createJpa(String description, int id, String name) {
    ItemEntity itemEntity = ItemEntity.builder()
        .description(description)
        .name(name)
        .category(TECHNOLOGY)
        .build();
    return itemService.save(itemEntity);
  }

  private void dummyMethodToTestPredicate() {
    Predicate<String> isShorterThan2 = (word) -> word.length() < 2;
  }
}
