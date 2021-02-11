package pl.pawel.cqrs.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
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
 * Service which is used for learning: - PersistenceContext, Transactional and Lazy annotations -
 * RequestContextHolder
 */
@RequiredArgsConstructor
@Service
public class NamesServiceImpl implements NamesService {

  private List<CachedName> names = new LinkedList<>();

  @PersistenceContext
  private final EntityManager entityManager;
  private final ItemService itemService;

  @Autowired
  @Lazy
  private LazyBeanService lazyBeanService;

  @Override
  public List<CachedName> getAll() {
    return createName();
  }

  public List<CachedName> createName() {
    CachedName cachedName = CachedName.builder()
        .name("Imie utworzone o: " + ofPattern("YYYY-MM-DD HH:mm:ss")
            .format(now()))
        .id(names.size())
        .build();
    names.add(cachedName);
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

  public String requestAttributes() {
    final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    requestAttributes.getAttribute("test", RequestAttributes.SCOPE_REQUEST);
    requestAttributes.setAttribute("test", null, RequestAttributes.SCOPE_SESSION);
    return "test RequestContextHolder";
  }

  private void dummyMethodToTestPredicate() {
    Predicate<String> isShorterThan2 = (word) -> word.length() < 2;
  }

  @Builder(toBuilder = true)
  public static class CachedName {

    int id;
    String name;
  }
}
