package domain.discount;

import domain.core.Category;
import domain.discount.DiscountType;

public class Campaign {
    private Category category;
    private Double discount;
    private int minimumProductAmount;
    private DiscountType type;

    public Campaign(Category category, Double discount, int minimumProductAmount, DiscountType type) {
        this.category = category;
        this.discount = discount;
        this.minimumProductAmount = minimumProductAmount;
        this.type = type;
    }
}
