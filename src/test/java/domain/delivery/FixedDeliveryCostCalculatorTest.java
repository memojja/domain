package domain.delivery;

import domain.cart.ShoppingCart;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FixedDeliveryCostCalculatorTest {

    @ParameterizedTest
    @MethodSource("getArguments")
    public void givenShoppingCart_whenCalculateDeliveryCost_ThenCalculateTrulyTheFormula(long numberOfDelivery,long numberOfProducts,double costPerDelivery,double costPerProduct,double fixedCost){
        //given
        ShoppingCart shoppingCart = mock(ShoppingCart.class);
        when(shoppingCart.getNumberOfDeliveries()).thenReturn(numberOfDelivery);
        when(shoppingCart.getNumberOfProducts()).thenReturn(numberOfProducts);

        DeliveryCostCalculator deliveryCostCalculator = new FixedDeliveryCostCalculator(costPerDelivery,costPerProduct,fixedCost);

        //when
        double actual = deliveryCostCalculator.calculateFor(shoppingCart);

        //then
        double expected = (numberOfDelivery*costPerDelivery) + (numberOfProducts*costPerProduct) + fixedCost;
        assertEquals(expected,actual);
    }

    private static Stream<Arguments> getArguments(){
        return Stream.of(
                Arguments.of(1,1,1,1,1),
                Arguments.of(0,0,0.0,0.0,2.99),
                Arguments.of(110L,1110L,2232.22,1233.,2.99)
        );
    }
}
