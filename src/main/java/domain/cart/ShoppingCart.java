package domain.cart;

import domain.delivery.DeliveryCostCalculator;
import domain.delivery.FixedDeliveryCostCalculator;
import domain.discount.campaign.Campaign;
import domain.discount.coupon.Coupon;
import domain.core.Category;
import domain.core.Product;
import domain.core.ProductQuantityHolder;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCart implements CouponDiscountApplicable, CampainDiscountApplicable {

    //TODO key will be domain.product id
    private Map<Product, ProductQuantityHolder> products;
    private DeliveryCostCalculator deliveryCostCalculator;

    //TODO think about cohesion...
    private double campaignDiscount;
    private double couponDiscount;

    public ShoppingCart(){
        products = new HashMap<>();
        deliveryCostCalculator = new FixedDeliveryCostCalculator( 2.99,2.0, 3.0);
    }

    public ShoppingCart(DeliveryCostCalculator deliveryCostCalculator){
        products = new HashMap<>();
        deliveryCostCalculator = deliveryCostCalculator;
    }

    public void addItem(Product product,int amount){
        ProductQuantityHolder productQuantityHolder = products.get(product);

        //TODO think about it
        if(productQuantityHolder != null){
            productQuantityHolder.increaseQuantity(amount);
        }else {
            productQuantityHolder = new ProductQuantityHolder(product,amount);
        }

        products.put(product,productQuantityHolder);
    }


    public Map<Product, ProductQuantityHolder> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public long getNumberOfDeliveries() {
        return getCategories().size();
    }

    public long getNumberOfProducts() {
        return products.values().stream()
                .mapToInt(holder -> holder.getQuantity().get())
                .sum();
    }

    public Set<Category> getCategories(){
        return products.values().stream()
                .map(productQuantityHolder -> productQuantityHolder.getProduct().getCategory())
                .collect(Collectors.toSet());
    }

    @Override
    public void applyCoupon(Coupon coupon) {
        this.couponDiscount = coupon.calculateDiscount(this);
    }

    @Override
    public double getCouponDiscount() {
        return this.couponDiscount;
    }

    @Override
    public void applyDiscounts(Campaign... campaigns) {
        this.campaignDiscount =  Arrays.stream(campaigns)
                .mapToDouble(campaign -> campaign.calculateDiscount(this))
                .max()
                .getAsDouble();
    }

    @Override
    public double getCampaingDiscount() {
        return campaignDiscount;
    }

    public double getTotalAmountsAfterDiscount(){
        return Math.max(0,getTotalProductPrice()-campaignDiscount-couponDiscount);
    }

    public double getTotalProductPrice() {
        return products.values().stream()
                .mapToDouble(holder -> holder.getProduct().getPrice() * holder.getQuantity().get())
                .sum();
    }

    public double getDeliveryCost() {
        return deliveryCostCalculator.calculateFor(this);
    }

    public Set<ProductQuantityHolder> getProductsInCategory(Category category){
        return products.values().stream()
                .filter(productQuantityHolder -> productQuantityHolder.getProduct().getCategory().equals(category) ||
                        Category.isCategoryFits(category,productQuantityHolder.getProduct().getCategory()))
                .collect(Collectors.toSet());
    }

}
