package pl.pawel.cqrs.service;

import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
import static java.util.stream.Collectors.toList;
import static pl.pawel.cqrs.domain.ItemCategory.TECHNOLOGY;

/**
 * Service which is used for learning: - PersistenceContext, Transactional and Lazy annotations -
 * RequestContextHolder Caching
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class NamesServiceImpl implements NamesService {

  private List<CachedName> names = new LinkedList<>();

  private final CacheManager cacheManager;

  @PersistenceContext
  private final EntityManager entityManager;
  private final ItemService itemService;

  @Autowired
  @Lazy
  private LazyBeanService lazyBeanService;

  @CachePut(value = "names", key="#id")
  public CachedName createName(int id) {
    log.info("execution method to add cached elements");
    CachedName cachedName = CachedName.builder()
        .name("Imie utworzone o: " + ofPattern("YYYY-MM-DD HH:mm:ss")
            .format(now()))
        .id(id)
        .build();
    names.add(cachedName);
    return cachedName;
  }

  @Cacheable(value = "names", key="#id")
  @Override
  public Optional<CachedName> getById(int id) {
    log.info("execution method to get by id cached elements");
    return names.stream()
        .filter(n -> n.getId() == id)
        .findFirst();
  }

  @Cacheable("names")
  @Override
  public List<CachedName> getAll() {
    log.info("execution method to get cached elements");
    return names;
  }

  @CacheEvict(value = "names", key="#id")
  public CachedName updateName(int id, String name) {
    log.info("execution method to update cached elements");
    CachedName cachedName = CachedName.builder()
        .name("Imie zmienione na: " + name + " o: "
            + ofPattern("YYYY-MM-DD HH:mm:ss").format(now()))
        .id(id)
        .build();
    Optional<CachedName> optionalCachedName = names.stream()
        .filter(n -> n.getId() == id)
        /*.peek(n -> n.setName("Imie zmienione na: " + name + " o: "
            + ofPattern("YYYY-MM-DD HH:mm:ss").format(now())))*/
        .findFirst();
    optionalCachedName.ifPresent(n -> n.setName("Imie zmienione na: " + name + " o: "
        + ofPattern("YYYY-MM-DD HH:mm:ss").format(now())));
    //names.add(cachedName);
    return cachedName;
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
  @Data
  public static class CachedName {

    private int id;
    private String name;
  }
}
