package ui;

import domain.cart.ShoppingCart;
import domain.core.Category;
import domain.core.Product;
import domain.core.ProductQuantityHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsolePrinter implements Printer {

    private StringBuilder stringBuilder;

    @Override
    public void print(ShoppingCart shoppingCart) {
        stringBuilder = new StringBuilder();

        System.out.println(
                );

        shoppingCart.getProducts().values().stream()
                .collect(Collectors.groupingBy(productQuantityHolder -> productQuantityHolder.getProduct().getCategory()))
                .forEach((category, productQuantityHolders) ->  {
                    stringBuilder.append(String.format("---- Category %s -----\n", category.getTitle()));
                    shoppingCart.getProducts().values().stream()
                            .filter(productQuantityHolder -> productQuantityHolder.getProduct().getCategory().equals(category))
                            .forEach(this::toString);
                });




        stringBuilder.append("----------\n");
        stringBuilder.append("Total Price ").append(shoppingCart.getTotalProductPrice()).append(" \t")
                .append("Total Discounted ").append(shoppingCart.getTotalAmountsAfterDiscount()).append(" \t")
                .append("Delivery Cost ").append(shoppingCart.getDeliveryCost());

        System.out.println(stringBuilder.toString());


    }

    public void toString(ProductQuantityHolder holder){
        Product product = holder.getProduct();
        stringBuilder.append(String.format("Product: %s\t",product.getTitle()))
                .append(String.format("Quantity: %d\t",holder.getQuantity().get()))
                .append("\n");
    }
}
