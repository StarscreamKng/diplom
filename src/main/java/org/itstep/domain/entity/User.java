package org.itstep.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/*
  create table users(
      username varchar_ignorecase(50) not null primary key,
      password varchar_ignorecase(50) not null,
      enabled boolean not null);
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
@ToString(exclude = "author")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    @Column(name = "username", unique = true, length = 50, nullable = false)
    String login;

    @NonNull
    @Column(name = "password", nullable = false, length = 100)
    String password;

    @Column(name = "enabled", nullable = false)
    Boolean enabled = true;

    @OneToMany(mappedBy = "user")
    List<Authority> roles;

    @OneToOne
    Author author;
}
