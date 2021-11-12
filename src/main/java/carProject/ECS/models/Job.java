package carProject.ECS.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Getter @Setter
    private String target;
    @Getter @Setter
    private String criteria;
    @Getter @Setter
    private String value;
}
