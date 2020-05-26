package domain.cart;

import domain.discount.campaign.Campaign;

public interface CampainDiscountApplicable {
    void applyDiscounts(Campaign... campaigns);
    double getCampaingDiscount();
}
