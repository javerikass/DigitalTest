package ru.digitalchief.test.dto;

import java.util.List;
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
public class PlayCharacterDto {

  private long id;
  private String name;
  private int level;
  private int health;
  private int strength;
  private int stamina;
  private List<ItemDto> itemsDto;
}
