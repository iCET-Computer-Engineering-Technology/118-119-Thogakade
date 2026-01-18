package model.tm;

import javafx.scene.Parent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemTM {
    private String code;
    private String description;
    private String packSize;
    private Double unitPrice;
    private Integer stock;
}
