package ui;


import domain.cart.ShoppingCart;
import domain.core.Category;
import domain.core.Product;
import domain.discount.DiscountType;
import domain.discount.campaign.Campaign;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConsolePrinterIT {

    @Test
    public void givenShoppingCart_whenPrint_thenResultMustBeInConsole(){
        //given
        List<String> names = Arrays.asList("Foods","Clothes","Books");

        ShoppingCart shoppingCart = new ShoppingCart();

        List<Category> categories = generateCategoriesByName(names);
        List<Campaign> campaigns = generateCampaignForCategories(categories);
        List<Product> products = genereateProductsByNameInCategories(names, categories);

        //when
        products.forEach(product -> shoppingCart.addItem(product,1));
        shoppingCart.applyDiscounts(campaigns);

        shoppingCart.print();
    }

    private List<Campaign> generateCampaignForCategories(List<Category> categories) {
        return categories.stream()
                .map(category -> new Campaign(category,10.0,2, DiscountType.AMOUNT))
                .collect(Collectors.toList());
    }

    private List<Category> generateCategoriesByName(List<String> names) {
        return names.stream()
                .map(Category::new)
                .collect(Collectors.toList());
    }

    private List<Product> genereateProductsByNameInCategories(List<String> names, List<Category> categories) {
        return categories.stream()
                .flatMap(category ->
                            names.stream()
                                    .flatMap(genereteProduct(category))
                )
                .collect(Collectors.toList());
    }

    private Function<String, Stream<? extends Product>> genereteProduct(Category category) {
        return name ->
                IntStream.range(0,5)
                        .mapToObj( i -> name + "_" + i)
                        .map(productName -> new Product(productName,1.5,category));
    }
}
