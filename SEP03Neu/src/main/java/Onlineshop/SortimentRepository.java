package Onlineshop;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SortimentRepository extends JpaRepository<Sortiment, Long> {

    Optional<Sortiment> findeMitName(String name);

    Optional<Sortiment> findeAnzahl(Integer anzahl);
}
