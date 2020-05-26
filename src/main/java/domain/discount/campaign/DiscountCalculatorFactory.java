package domain.discount.campaign;

import domain.discount.DiscountType;

public class DiscountCalculatorFactory {

    public static CampaignDiscountCalculator getCampaignDiscountCalculator(DiscountType discountType){
        if(DiscountType.RATE.equals(discountType)){
            return new RateCampaignDiscountCalculator();
        } else if(DiscountType.AMOUNT.equals(discountType)){
            return new AmountCampaignDiscountCalculator();
        } else {
          throw new RuntimeException("Wrong Discount Type!");
        }
    }

}
