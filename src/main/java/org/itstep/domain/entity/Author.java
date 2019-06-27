package org.itstep.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
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

  @NonNull
  @NotBlank
  @Column(nullable = false)
  private String name;

  @NonNull
  @NotBlank
  @Column(nullable = false)
  private String surname;

  @NonNull
  @Email
  @Column(unique = true)
  private String email;

  @NonNull
  @NotBlank
  @Column(nullable = false)
  private String password;

  @NonNull
  private Status status;

  @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
  private List<Post> posts;

  @NonNull
  @Column(columnDefinition = "text")
  private String about;

  @NonNull
  private String avatarUrl;

  public String getFullName() {
    return getName() + " " + getSurname();
  }

  @OneToOne(mappedBy = "author")
  User user;
}
