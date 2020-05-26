package domain.discount.campaign;

import domain.cart.ShoppingCart;
import domain.core.ProductQuantityHolder;

import java.util.Set;

public class RateCampaignDiscountCalculator implements CampaignDiscountCalculator {
    @Override
    public double calculate(Campaign campaign, ShoppingCart shoppingCart) {
        //TODO apply template pattern
        Set<ProductQuantityHolder> productsInCategory =  shoppingCart.getProductsInCategory(campaign.getCategory());
        int numberOfItemsOnCategory = productsInCategory.stream()
                .mapToInt(productQuantityHolder -> productQuantityHolder.getQuantity().get())
                .sum();

        if(numberOfItemsOnCategory >= campaign.getMinimumProductAmount()){
            return productsInCategory.stream()
                    .mapToDouble(holder -> holder.getProduct().getPrice() * holder.getQuantity().get())
                    .map(productTotalPrice -> productTotalPrice*(campaign.getDiscount()/100))
                    .sum();
        }
        return 0;
    }
}
