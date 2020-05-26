package domain.delivery;

import domain.cart.ShoppingCart;

public interface DeliveryCostCalculator {
    double calculateFor(ShoppingCart cart);
}
