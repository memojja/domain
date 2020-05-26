package domain.cart;

import domain.discount.Campaign;
import domain.discount.Coupon;
import domain.core.Category;
import domain.core.Product;
import domain.core.ProductQuantityHolder;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCart implements CouponDiscountApplicable, CampainDiscountApplicable {

    //TODO key will be domain.product id
    private Map<Product, ProductQuantityHolder> products;
    private double campaignDiscount;
    private double couponDiscount;

    public ShoppingCart(){
        products = new HashMap<>();
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
        return products.size();
    }

    private Set<Category> getCategories(){
        return products.values().stream()
                .map(productQuantityHolder -> productQuantityHolder.getProduct().getCategory())
                .collect(Collectors.toSet());
    }

    @Override
    public void applyCoupon(Coupon coupon) {

    }

    @Override
    public void applyDiscounts(Campaign... campaigns) {

    }
}
