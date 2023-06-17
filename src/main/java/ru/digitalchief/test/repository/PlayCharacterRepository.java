package ru.digitalchief.test.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.digitalchief.test.entity.PlayCharacter;

public interface PlayCharacterRepository extends JpaRepository<PlayCharacter, Long> {

  Optional<PlayCharacter> findPlayCharacterByName(String name);

  @Query("SELECT pc FROM PlayCharacter pc JOIN FETCH pc.itemsList WHERE pc.id = :id")
  Optional<PlayCharacter> findByIdWithItems(@Param("id") Long id);

  @Modifying
  @Query("UPDATE PlayCharacter c SET c.name=:name, c.health=:health, c.level=:level, c.stamina=:stamina, c.strength=:strength where c.id=:id")
  void updatePlayCharacter(@Param("id") long id,
      @Param("name") String name,
      @Param("health") int health,
      @Param("level") int level,
      @Param("stamina") int stamina,
      @Param("strength") int strength);
}
