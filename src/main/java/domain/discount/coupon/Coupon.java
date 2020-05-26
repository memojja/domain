package domain.discount.coupon;

import domain.cart.ShoppingCart;
import domain.discount.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Coupon {

    private double minimumProductAmount;
    private double discount;
    private DiscountType type;

    public double calculateDiscount(ShoppingCart shoppingCart){
        if(shoppingCart.getTotalProductPrice() < minimumProductAmount){
            return  0;
        }
        if(type == DiscountType.AMOUNT){
            return discount;
        }
        else{
            return  (shoppingCart.getTotalProductPrice()*discount)/100;
        }

    }
}
