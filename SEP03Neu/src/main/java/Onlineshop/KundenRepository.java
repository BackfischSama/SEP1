package Onlineshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KundenRepository extends JpaRepository<Kunde, Long> {


    static boolean existsByEmail(String email) {
    }
}
