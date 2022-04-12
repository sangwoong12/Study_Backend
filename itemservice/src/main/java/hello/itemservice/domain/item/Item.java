package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
//@Setter @Getter
public class Item {

    private Long id;
    public String itemName;
    public Integer price=0;
    private Integer quantity;

    public Item(){

    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
