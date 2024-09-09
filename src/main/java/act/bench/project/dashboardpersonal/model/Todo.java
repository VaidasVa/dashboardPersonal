package act.bench.project.dashboardpersonal.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo implements Serializable {

    private UUID id;
    private String content;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean status;
    private boolean archived;
}
