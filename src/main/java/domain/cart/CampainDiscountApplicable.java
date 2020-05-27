package domain.cart;

import domain.discount.campaign.Campaign;

import java.util.List;

public interface CampainDiscountApplicable {
    void applyDiscounts(Campaign... campaigns);
    void applyDiscounts(List<Campaign> campaigns);
    double getCampaingDiscount();
}
