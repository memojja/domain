package domain.delivery;

import domain.cart.ShoppingCart;

public class FixedDeliveryCostCalculator implements DeliveryCostCalculator {

    private final double costPerProduct;
    private double costPerDelivery;
    private double fixedCost;

    public FixedDeliveryCostCalculator(double costPerDelivery, double costForProduct, double fixedCost){
        this.fixedCost = fixedCost;
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costForProduct;
    }

    @Override
    public double calculateFor(ShoppingCart cart) {
        long numberOfDelivery = cart.getNumberOfDeliveries();
        long numberOfProducts = cart.getNumberOfProducts();
        return getDeliveryCost(numberOfDelivery) + getProductCost(numberOfProducts) + fixedCost;
    }

    private double getDeliveryCost(long numberOfDelivery){
        return numberOfDelivery * costPerDelivery;
    }

    private double getProductCost(long numberOfProducts){
        return numberOfProducts * costPerProduct;
    }
}
