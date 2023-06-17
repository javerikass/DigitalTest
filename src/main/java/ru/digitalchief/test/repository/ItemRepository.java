package ru.digitalchief.test.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.digitalchief.test.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

  Optional<Item> findItemByName(String name);

  @Modifying
  @Query("UPDATE Item i SET i.name=:name, i.description=:description, i.price=:price, i.quantity=:quantity, i.weight=:weight where i.id=:id")
  void updateItem(@Param("id") long id,
      @Param("name") String name,
      @Param("description") String description,
      @Param("price") double price,
      @Param("quantity") int quantity,
      @Param("weight") double weight);

}
