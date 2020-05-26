package domain.discount.campaign;

import domain.cart.ShoppingCart;

interface IsCampaignApplicable {
    default boolean isApplicable(Campaign campaign, ShoppingCart shoppingCart) {
        return shoppingCart.getNumberOfProducts() >= campaign.getMinimumProductAmount();
    }
}
