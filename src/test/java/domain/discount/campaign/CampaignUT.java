package domain.discount.campaign;

import domain.cart.ShoppingCart;
import domain.core.Category;
import domain.core.Product;
import domain.core.ProductQuantityHolder;
import domain.discount.DiscountType;
import domain.discount.campaign.Campaign;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CampaignUT {

    @Test
    public void givenValidDCampaign_whenCalculateDiscount_thenDiscountMustbeAboveZero(){
        //given
        Category foods = new Category("Foods");
        Campaign campaign = new Campaign(foods,10.0,5, DiscountType.AMOUNT);
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        Set<ProductQuantityHolder> products = new HashSet<>();
        products.add(new ProductQuantityHolder(new Product("Apple",50,foods),5));
        when(shoppingCart.getProductsInCategory(foods)).thenReturn(products);

        //when
        double discount = campaign.calculateDiscount(shoppingCart);

        //then
        assertEquals(discount,10);
    }

    @Test
    public void givenNonValidDCampaign_whenCalculateDiscount_thenDiscountMustbeero(){
        //given
        Category foods = new Category("Foods");
        Campaign campaign = new Campaign(foods,10.0,15, DiscountType.AMOUNT);
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        Set<ProductQuantityHolder> products = new HashSet<>();
        products.add(new ProductQuantityHolder(new Product("Apple",50,foods),5));
        when(shoppingCart.getProductsInCategory(foods)).thenReturn(products);

        //when
        double discount = campaign.calculateDiscount(shoppingCart);

        //then
        assertEquals(discount,0);
    }
}
