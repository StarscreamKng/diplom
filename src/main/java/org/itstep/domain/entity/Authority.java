package org.itstep.domain.entity;

import lombok.*;

import javax.persistence.*;

/*
  create table authorities (
      username varchar_ignorecase(50) not null,
      authority varchar_ignorecase(50) not null,
      constraint fk_authorities_users foreign key(username) references users(username));
      create unique index ix_auth_username on authorities (username,authority);
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "authorities", uniqueConstraints = @UniqueConstraint(columnNames = {"id", "authority"}))
@ToString(exclude = "user")
public class Authority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    @Column(name = "authority", nullable = false)
    String role;

    @ManyToOne
    User user;
}
