package domain.core;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class ProductQuantityHolder {
    private Product product;
    private AtomicInteger quantity;

    public ProductQuantityHolder(Product product, int quantity) {
        this.product = product;
        this.quantity = new AtomicInteger(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductQuantityHolder)) return false;
        ProductQuantityHolder that = (ProductQuantityHolder) o;
        return getProduct().equals(that.getProduct());
    }

    @Override
    public int hashCode() {
        return getProduct().hashCode();
    }

    public int increaseQuantity(final int quantity){
        return this.quantity.updateAndGet(oldQuantity -> oldQuantity + quantity);
    }
}
