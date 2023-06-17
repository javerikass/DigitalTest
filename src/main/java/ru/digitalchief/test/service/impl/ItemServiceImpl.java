package ru.digitalchief.test.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalchief.test.dto.ItemDto;
import ru.digitalchief.test.entity.Item;
import ru.digitalchief.test.exception.ItemException;
import ru.digitalchief.test.mapper.ItemMapper;
import ru.digitalchief.test.repository.ItemRepository;
import ru.digitalchief.test.service.ItemService;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final ItemRepository itemRepository;
  private final ItemMapper mapper;

  @Override
  public ItemDto getItemByName(String name) {
    Item item = itemRepository.findItemByName(name)
        .orElseThrow(() -> ItemException.getItemException("No such item", HttpStatus.NOT_FOUND));
    return mapper.convertItemToItemDto(item);
  }

  @Override
  public void updateItem(ItemDto itemDto) {
    Item item = mapper.convertItemDtoToItem(itemDto);
    itemRepository.findById(item.getId())
        .orElseThrow(() -> ItemException.getItemException("Item not found", HttpStatus.NOT_FOUND));
    itemRepository.updateItem(item.getId(), item.getName(),
        item.getDescription(), item.getPrice(),
        item.getQuantity(), item.getWeight());
  }

  @Override
  public void deleteItem(Long id) {
    itemRepository.findById(id)
        .orElseThrow(() -> ItemException.getItemException("Item not found", HttpStatus.NOT_FOUND));
    itemRepository.deleteById(id);
  }

  @Override
  public void saveItem(ItemDto item) {
    itemRepository.save(mapper.convertItemDtoToItem(item));
  }

  @Override
  public List<ItemDto> findAll() {
    List<Item> items = itemRepository.findAll();
    if (items.isEmpty()) {
      throw ItemException.getItemException("No items found", HttpStatus.NOT_FOUND);
    }
    return mapper.convertListItemToListDto(items);
  }
}
