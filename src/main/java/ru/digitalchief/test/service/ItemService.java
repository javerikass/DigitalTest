package ru.digitalchief.test.service;

import java.util.List;
import ru.digitalchief.test.dto.ItemDto;

public interface ItemService {

  ItemDto getItemByName(String name);

  void updateItem(ItemDto item);

  void deleteItem(Long id);

  void saveItem(ItemDto item);

  List<ItemDto> findAll();

}
