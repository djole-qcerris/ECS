package carProject.ECS.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ThirdParty {
    @Getter @Setter
    private int tcp_id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String api_url;
    @Getter @Setter
    private String api_key;
}
