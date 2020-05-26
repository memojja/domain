package domain.cart;

import domain.cart.ShoppingCart;
import domain.core.Category;
import domain.core.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {

    @Test
    public void givenAvailableProduct_whenAddCart_thenProductsQuantityIncrease(){
        //given
        ShoppingCart shoppingCart = new ShoppingCart();
        Product banana = new Product("Banana",5,new Category());
        Product anotherBanana = new Product("Banana",5,new Category());

        //when
        shoppingCart.addItem(banana,20);
        shoppingCart.addItem(anotherBanana,20);

        //then
        int totalAmount = shoppingCart.getProducts().get(banana).getQuantity().get();
        assertEquals(totalAmount,40);
    }
}
