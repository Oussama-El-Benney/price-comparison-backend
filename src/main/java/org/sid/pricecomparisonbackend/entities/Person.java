package org.sid.pricecomparisonbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.pricecomparisonbackend.enums.PersonNature;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 6)
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  private String username;
  private String email;
  private String password;
  @Enumerated(EnumType.STRING)
  private PersonNature nature;

}
