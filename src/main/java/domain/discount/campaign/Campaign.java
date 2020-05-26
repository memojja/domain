package domain.discount.campaign;

import domain.cart.ShoppingCart;
import domain.core.Category;
import domain.discount.DiscountType;
import lombok.Data;

@Data
public class Campaign {
    private Category category;
    private Double discount;
    private int minimumProductAmount;
    private DiscountType type;
    private DiscountCalculator discountCalculator;

    public Campaign(Category category, Double discount, int minimumProductAmount, DiscountType type) {
        this.category = category;
        this.discount = discount;
        this.minimumProductAmount = minimumProductAmount;
        this.type = type;
        discountCalculator = DiscountCalculatorFactory.getCampaignDiscountCalculator(type);
    }

    public double calculateDiscount(ShoppingCart shoppingCart) {
        return this.discountCalculator.calculate(this,shoppingCart);
    }

}
