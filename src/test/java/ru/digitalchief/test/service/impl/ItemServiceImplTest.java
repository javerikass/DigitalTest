package ru.digitalchief.test.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ru.digitalchief.test.dto.ItemDto;
import ru.digitalchief.test.entity.Item;
import ru.digitalchief.test.exception.ItemException;
import ru.digitalchief.test.mapper.ItemMapper;
import ru.digitalchief.test.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

  @InjectMocks
  private ItemServiceImpl itemService;
  @Mock
  private ItemRepository itemRepository;
  @Mock
  private ItemMapper mapper;


  @Test
  void testGetItemByName() throws Exception {
    String itemName = "Test Item";
    Item item = Item.builder().name(itemName).build();
    ItemDto itemDto = ItemDto.builder().name(itemName).build();
    Mockito.when(itemRepository.findItemByName(itemName)).thenReturn(Optional.of(item));
    Mockito.when(mapper.convertItemToItemDto(item)).thenReturn(itemDto);

    ItemDto result = itemService.getItemByName(itemName);

    assertEquals(itemDto, result);
    Mockito.verify(itemRepository, Mockito.times(1)).findItemByName(itemName);
    Mockito.verify(mapper, Mockito.times(1)).convertItemToItemDto(item);
  }

  @Test
  void testGetItemByNameNotFound() throws Exception {
    String itemName = "Non-existent Item";
    assertThrowsExactly(ItemException.class, () -> itemService.getItemByName(itemName));
  }

  @Test
  void updateItem() {

  }

  @Test
  void testUpdateItem() {
    ItemDto itemDto = ItemDto.builder()
        .id(1L)
        .name("Test Item")
        .description("Test Description")
        .price(10.0)
        .quantity(5)
        .weight(1.0)
        .build();

    Item item = Item.builder()
        .id(1L)
        .name("Test Item")
        .description("Test Description")
        .price(10.0)
        .quantity(5)
        .weight(1.0)
        .build();

    Mockito.when(mapper.convertItemDtoToItem(itemDto)).thenReturn(item);
    Mockito.when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

    itemService.updateItem(itemDto);

    Mockito.verify(itemRepository, Mockito.times(1)).updateItem(
        item.getId(), item.getName(), item.getDescription(), item.getPrice(),
        item.getQuantity(), item.getWeight());
  }

  @Test
  void testUpdateItemWhenItemNotFound() {
    ItemDto itemDto = ItemDto.builder().build();
    Item item = Item.builder().build();

    Mockito.when(mapper.convertItemDtoToItem(itemDto)).thenReturn(item);
    Mockito.when(itemRepository.findById(itemDto.getId())).thenReturn(Optional.empty());

    ItemException exception = Assertions.assertThrows(ItemException.class, () -> {
      itemService.updateItem(itemDto);
    });

    assertEquals("Item not found", exception.getMessage());
    assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus());

    Mockito.verify(itemRepository, Mockito.never()).updateItem(
        Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble(),
        Mockito.anyInt(), Mockito.anyDouble());
  }

  @Test
  void testDeleteItem() {
    Long itemId = 1L;

    Mockito.when(itemRepository.findById(itemId)).thenReturn(Optional.of(new Item()));

    itemService.deleteItem(itemId);

    Mockito.verify(itemRepository, Mockito.times(1)).deleteById(itemId);
  }

  @Test
  void testDeleteItemWhenItemNotFound() {
    Long itemId = 1L;

    Mockito.when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

    ItemException exception = Assertions.assertThrows(ItemException.class, () -> {
      itemService.deleteItem(itemId);
    });

    assertEquals("Item not found", exception.getMessage());
    assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus());

    Mockito.verify(itemRepository, Mockito.never()).deleteById(Mockito.anyLong());
  }

  @Test
  void testSaveItem() {
    ItemDto itemDto = ItemDto.builder()
        .name("Test Item")
        .description("Test Description")
        .price(10.0)
        .quantity(5)
        .weight(1.0)
        .build();

    Item item = mapper.convertItemDtoToItem(itemDto);
    Mockito.when(itemRepository.save(item)).thenReturn(item);

    itemService.saveItem(itemDto);

    Mockito.verify(itemRepository, Mockito.times(1)).save(item);
  }

  @Test
  void testFindAll() {
    List<Item> items = new ArrayList<>();
    items.add(new Item());
    items.add(new Item());

    Mockito.when(itemRepository.findAll()).thenReturn(items);
    Mockito.when(mapper.convertListItemToListDto(items)).thenReturn(
        Arrays.asList(ItemDto.builder().build(), ItemDto.builder().build()));

    List<ItemDto> result = itemService.findAll();

    assertEquals(2, result.size());
    Mockito.verify(itemRepository, Mockito.times(1)).findAll();
    Mockito.verify(mapper, Mockito.times(1)).convertListItemToListDto(items);
  }

  @Test
  void testFindAllWhenNoItemsFound() {
    Mockito.when(itemRepository.findAll()).thenReturn(Collections.emptyList());

    ItemException exception = Assertions.assertThrows(ItemException.class, () -> {
      itemService.findAll();
    });

    assertEquals("No items found", exception.getMessage());
    assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatus());

    Mockito.verify(itemRepository, Mockito.times(1)).findAll();
    Mockito.verify(mapper, Mockito.never()).convertListItemToListDto(Mockito.anyList());
  }
}