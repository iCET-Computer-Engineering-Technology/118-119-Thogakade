package model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTM {
    private String code;
    private String orderId;
    private String description;
    private Double unitPrice;
    private Integer qtyOnHand;
    private Double total;
}
