package domain.cart;

import domain.discount.Campaign;

public interface CampainDiscountApplicable {
    void applyDiscounts(Campaign... campaigns);
}
