package Onlineshop;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarenkorbRepository extends JpaRepository<Warenkorb, Long> {
    Optional<Warenkorb> findeMitNamen(String name);
}
