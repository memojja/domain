package domain.discount.campaign;

import domain.cart.ShoppingCart;
import domain.core.ProductQuantityHolder;

import java.util.Set;

public class AmountCampaignDiscountCalculator implements DiscountCalculator, IsCampaignApplicable {

    @Override
    public double calculate(Campaign campaign, ShoppingCart shoppingCart) {
        //TODO apply template pattern
        Set<ProductQuantityHolder> productsInCategory =  shoppingCart.getProductsInCategory(campaign.getCategory());
        int numberOfItemsOnCategory = productsInCategory.stream()
                .mapToInt(productQuantityHolder -> productQuantityHolder.getQuantity().get())
                .sum();

        if(numberOfItemsOnCategory >= campaign.getMinimumProductAmount()){
            return campaign.getDiscount();
        }
        return 0;
    }

}
