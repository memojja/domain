package domain.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductUT {

    @Test
    void product_referance_test() {
        Category foodCategory = new Category("Food");
        Category anotherFoodCategory = new Category("AnotherFood");

        Product appleFoodCategory = new Product("Apple",10,foodCategory);
        Product appleSameFoodCategory = new Product("Apple",10,foodCategory);
        Product appleAnotherFoodCategory = new Product("Apple",10,anotherFoodCategory);

        assertTrue(appleFoodCategory.equals(appleSameFoodCategory));
        assertFalse(appleFoodCategory.equals(appleAnotherFoodCategory));
    }

}