package domain.discount.campaign;

import domain.cart.ShoppingCart;
import domain.discount.campaign.Campaign;

public interface CampaignDiscountCalculator {
    double calculate(Campaign campaign, ShoppingCart shoppingCart);
}
