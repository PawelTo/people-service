package pl.pawel.cqrs.service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@Service
public class NamesServiceImpl implements NamesService {

    private List<String> names = new LinkedList<>();

    @Override
    public List<String> getAll() {
        return createNames();
    }

    private List<String> createNames() {
        names.add("Imie utworzone o: " + ofPattern("YYYY-MM-DD HH:mm:ss")
                                                          .format(now()));
        return names;
    }
}
