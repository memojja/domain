package domain.core;

import domain.core.Category;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    @Test
    public void givenCategory_whenAddedSubCategory_thenSupported(){
        //given
        Category clothes = new Category("Clothes");

        //when
        Category skirts = new Category("Skirts");
        clothes.setParent(skirts);

    }
}
