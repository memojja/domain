package domain.discount;

import domain.cart.ShoppingCart;
import domain.core.Category;
import domain.discount.campaign.Campaign;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class CampaignTest {

    @Test
    public void given2AmountCampaign_whenApplyDiscountsOnCart_thenApplyMaxDiscount(){
        //given
        Category foods = new Category("Foods");
        Campaign campaign = new Campaign(foods,10.0,5, DiscountType.AMOUNT);
        Campaign campaign2 = new Campaign(foods,20.0,5,DiscountType.AMOUNT);
        ShoppingCart shoppingCart = mock(ShoppingCart.class);

        //when
        shoppingCart.applyDiscounts(campaign,campaign2);

        //then

    }
}
