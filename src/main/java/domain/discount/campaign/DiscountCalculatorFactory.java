package domain.discount.campaign;

import domain.discount.DiscountType;
import domain.discount.coupon.AmountCouponDiscountCalculator;
import domain.discount.coupon.CouponDiscountCalculator;
import domain.discount.coupon.RateCouponDiscountCalculator;

//TODO make abstract factory..
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

    public static CouponDiscountCalculator getCouponDiscountCalculator(DiscountType discountType){
        if(DiscountType.RATE.equals(discountType)){
            return new RateCouponDiscountCalculator();
        } else if(DiscountType.AMOUNT.equals(discountType)){
            return new AmountCouponDiscountCalculator();
        } else {
            throw new RuntimeException("Wrong Discount Type!");
        }
    }

}
