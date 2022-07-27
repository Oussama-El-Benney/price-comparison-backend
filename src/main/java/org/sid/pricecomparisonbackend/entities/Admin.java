package org.sid.pricecomparisonbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@DiscriminatorValue("ADMIN")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Admin extends Person{
  private Date connectionDate;

}
