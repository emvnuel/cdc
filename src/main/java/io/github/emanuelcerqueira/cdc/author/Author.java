package io.github.emanuelcerqueira.cdc.author;

import io.github.emanuelcerqueira.cdc.common.Default;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TB_AUTHOR")
public class Author {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Length(max = 400)
    private String bio;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Default
    public Author(@NotEmpty String name,
                  @NotEmpty @Email String email,
                  @NotEmpty @Length(max = 400) String bio) {
        this.name = name;
        this.email = email;
        this.bio = bio;
    }
}
