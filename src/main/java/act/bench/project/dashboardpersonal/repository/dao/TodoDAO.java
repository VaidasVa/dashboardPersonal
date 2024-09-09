package act.bench.project.dashboardpersonal.repository.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="todos")
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDAO {

    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    @JsonIgnore
    private LocalDateTime created;

    @UpdateTimestamp
    @JsonIgnore
    private LocalDateTime updated;

    @Column
    @JsonIgnore
    private boolean status;

    @Column
    @JsonIgnore
    private boolean archived;

}
