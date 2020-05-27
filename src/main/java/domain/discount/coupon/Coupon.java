package domain.discount.coupon;

import domain.cart.ShoppingCart;
import domain.discount.DiscountType;
import domain.discount.campaign.DiscountCalculatorFactory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Coupon {

    private double minimumProductAmount;
    private double discount;
    private DiscountType type;
    private CouponDiscountCalculator couponDiscountCalculator;

    public Coupon(double minimumProductAmount,double discount,DiscountType type) {
        this.minimumProductAmount = minimumProductAmount;
        this.discount = discount;
        this.type = type;
        couponDiscountCalculator = DiscountCalculatorFactory.getCouponDiscountCalculator(type);
    }

    public double calculateDiscount(ShoppingCart shoppingCart){
       return couponDiscountCalculator.calculate(this,shoppingCart);
    }
}
