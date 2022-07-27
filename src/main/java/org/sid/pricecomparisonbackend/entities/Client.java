package org.sid.pricecomparisonbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("CLIENT")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Client extends Person{
  private int telNumber;
  private String adresse;
}
