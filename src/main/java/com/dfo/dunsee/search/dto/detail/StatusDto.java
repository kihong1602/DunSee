package com.dfo.dunsee.search.dto.detail;

import lombok.Builder;
import lombok.Getter;

public class StatusDto {

  @Getter
  private int fame;
  private int strength;
  private int intelligence;
  private int vitality;
  private int spirit;
  private int physicalAttack;
  private int magicAttack;
  private int independentAttack;
  private double physicalDefense;
  private double magicDefense;
  private double attackSpeed;
  private double castingSpeed;
  private double moveSpeed;
  private int fireEnhance;
  private int coldEnhance;
  private int lightEnhance;
  private int darkEnhance;
  private int fireResist;
  private int coldResist;
  private int lightResist;
  private int darkResist;

  @Builder
  private StatusDto(int fame, int strength, int intelligence, int vitality, int spirit, int physicalAttack,
      int magicAttack,
      int independentAttack, double physicalDefense, double magicDefense, double attackSpeed, double castingSpeed,
      double moveSpeed, int fireEnhance, int coldEnhance, int lightEnhance, int darkEnhance, int fireResist,
      int coldResist, int lightResist, int darkResist) {
    this.fame = fame;
    this.strength = strength;
    this.intelligence = intelligence;
    this.vitality = vitality;
    this.spirit = spirit;
    this.physicalAttack = physicalAttack;
    this.magicAttack = magicAttack;
    this.independentAttack = independentAttack;
    this.physicalDefense = physicalDefense;
    this.magicDefense = magicDefense;
    this.attackSpeed = attackSpeed;
    this.castingSpeed = castingSpeed;
    this.moveSpeed = moveSpeed;
    this.fireEnhance = fireEnhance;
    this.coldEnhance = coldEnhance;
    this.lightEnhance = lightEnhance;
    this.darkEnhance = darkEnhance;
    this.fireResist = fireResist;
    this.coldResist = coldResist;
    this.lightResist = lightResist;
    this.darkResist = darkResist;
  }
}
