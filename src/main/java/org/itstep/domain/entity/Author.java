package org.itstep.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "posts")
@Entity
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "timestamp", name = "registration")
  @CreationTimestamp
  private LocalDateTime registration;

  @NotBlank
  @Column(nullable = false)
  private String name;

  @NotBlank
  @Column(nullable = false)
  private String surname;

  @Email
  @Column(unique = true)
  private String email;

  private Status status;

  @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
  private List<Post> posts;

  @Column(columnDefinition = "text")
  private String about;

  private String avatarUrl;

  public String getFullName() {
    return getName() + " " + getSurname();
  }
}
