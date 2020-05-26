package domain.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Product {
    private String title;
    private double price;
    private Category category;

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }
}
