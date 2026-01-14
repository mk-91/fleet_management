package fleet_management.repositories;

import java.util.List;
import java.util.Optional;

// Interfejs generyczny <T>, dzięki temu można go użyć do różnych typów w przyszłości
public interface Repository<T> {
    void save(T item);
    Optional<T> findById(long id);
    List<T> findAll();
    void delete(T item);
}