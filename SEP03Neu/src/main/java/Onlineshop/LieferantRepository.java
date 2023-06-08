package Onlineshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LieferantRepository extends JpaRepository<Lieferant, Long> {
}
