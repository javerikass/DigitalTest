package ru.digitalchief.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ItemDto {

  private long id;
  private String name;
  private int quantity;
  private double weight;
  private String description;
  private double price;
  private PlayCharacterDto character;
}
