package com.dfo.dunsee.domain.search.dto.detail.equip;

import com.dfo.dunsee.apiresponse.charequipment.enchant.Enchant;
import java.util.stream.Collectors;

public class EnchantDto {

  private String description;

  private EnchantDto(String statusValue) {
    this.description = statusValue;
  }

  public static EnchantDto createEnchantDto(Enchant enchant) {
    if (enchant == null) {
      return null;
    }
    String statusValue = enchant.getStatus()
                                .stream()
                                .map(status -> status.getName() + " + " + status.getValue() + " ")
                                .collect(
                                    Collectors.joining());
    return new EnchantDto(statusValue);
  }
}
