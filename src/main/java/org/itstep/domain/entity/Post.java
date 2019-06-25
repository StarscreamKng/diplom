package org.itstep.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER)
  private Author author;

  @Column(columnDefinition = "timestamp")
  @CreationTimestamp
  private LocalDateTime published;

  @NotBlank
  @Length(max=100)
  @Column(length = 100, nullable = false)
  private String title;

  @NotBlank
  @Column(columnDefinition = "text", nullable = false)
  private String text;

  private String imageUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  private Category category;

  @ManyToMany(fetch = FetchType.LAZY)
  private List<Tag> tags;

  private boolean wideImage = false;

  @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
  private List<Comment> comments;

  public String getDescription() {
    val tokenizer = new StringTokenizer(text);
    val stringBuilder = new StringBuilder();
    int i=0;
    while (tokenizer.hasMoreTokens() && i < 20) {
      stringBuilder.append(tokenizer.nextToken());
      stringBuilder.append(" ");
      i++;
    }
    return stringBuilder.toString();
  }
}
