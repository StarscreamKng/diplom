package org.itstep.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "post")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @Column(nullable = false)
  private String name;

  @Email
  @NotBlank
  @Column(nullable = false)
  private String email;

  @Column(columnDefinition = "timestamp", name = "commentDate")
  @CreationTimestamp
  private LocalDateTime commentDate;

  @Column
  @Length(max = 100)
  private String subject;

  @Column(nullable = false, columnDefinition = "text")
  @Length(max = 1000)
  private String comment;

  @ManyToOne(fetch = FetchType.EAGER)
  private Post post;
}
