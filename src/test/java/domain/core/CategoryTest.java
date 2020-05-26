package domain.core;

import domain.core.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    @Test
    public void givenCategory_whenAddedSubCategory_thenSupported(){
        //given
        Category clothes = new Category("Clothes");
        Category skirts = new Category("Skirts");
        Category skirts2 = new Category("Skirts");
        Category books = new Category("Books");

        //when
        clothes.setParent(skirts);

        //then
        assertTrue(Category.isCategoryFits(clothes,skirts));
        assertTrue(Category.isCategoryFits(clothes,skirts2));
        assertFalse(Category.isCategoryFits(clothes,books));
    }
}
