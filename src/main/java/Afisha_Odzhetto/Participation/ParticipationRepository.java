package Afisha_Odzhetto.Participation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {
    @Query("SELECT p FROM participation p WHERE p.user_id = ?1 AND p.event_id = ?2")
    Optional<Participation> findbyUseridEventid(int userId, int eventId);
}
