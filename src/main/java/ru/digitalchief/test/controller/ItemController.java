package ru.digitalchief.test.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalchief.test.dto.ItemDto;
import ru.digitalchief.test.service.ItemService;

@RestController
@RequestMapping("/v1/item")
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping("/")
  public List<ItemDto> getAllItems() {
    return itemService.findAll();
  }

  @GetMapping("/{name}")
  public ItemDto getItemByName(@PathVariable String name) {
    return itemService.getItemByName(name);
  }

  @PostMapping("/create")
  public ResponseEntity<Void> createItem(@RequestBody ItemDto item) {
    itemService.saveItem(item);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/update")
  public ResponseEntity<Void> updateItemById(@RequestBody ItemDto item) {
    itemService.updateItem(item);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping("/delete/{id}")
  public ResponseEntity<Void> deleteItemById(@PathVariable Long id) {
    itemService.deleteItem(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
