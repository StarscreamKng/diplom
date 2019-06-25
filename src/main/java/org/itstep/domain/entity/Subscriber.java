package org.itstep.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "timestamp", name = "subscribe_date")
    @CreationTimestamp
    private LocalDateTime subscribeDate;

    @Email
    @Column(unique = true)
    @NonNull
    private String email;

    private Boolean enabled = true;
}
