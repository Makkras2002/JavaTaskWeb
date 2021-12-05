package com.makkras.shop.entity;

import java.math.BigDecimal;

public class ComponentOrder extends CustomEntity{
    private Product product;
    private Long orderedProductAmount;
    private BigDecimal orderedProductFullPrice;
    public ComponentOrder(){
    }
    public ComponentOrder(Product product,
                          Long orderedProductAmount, BigDecimal orderedProductFullPrice) {
        this.product = product;
        this.orderedProductAmount = orderedProductAmount;
        this.orderedProductFullPrice = orderedProductFullPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getOrderedProductAmount() {
        return orderedProductAmount;
    }

    public void setOrderedProductAmount(Long orderedProductAmount) {
        this.orderedProductAmount = orderedProductAmount;
    }

    public BigDecimal getOrderedProductFullPrice() {
        return orderedProductFullPrice;
    }

    public void setOrderedProductFullPrice(BigDecimal orderedProductFullPrice) {
        this.orderedProductFullPrice = orderedProductFullPrice;
    }
    public void setOrderedProductFullPrice() {
        this.orderedProductFullPrice = product.getProductPrice().multiply(BigDecimal.valueOf(orderedProductAmount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentOrder that = (ComponentOrder) o;

        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (orderedProductAmount != null ? !orderedProductAmount.equals(that.orderedProductAmount) : that.orderedProductAmount != null)
            return false;
        return orderedProductFullPrice != null ? orderedProductFullPrice.equals(that.orderedProductFullPrice) : that.orderedProductFullPrice == null;
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (orderedProductAmount != null ? orderedProductAmount.hashCode() : 0);
        result = 31 * result + (orderedProductFullPrice != null ? orderedProductFullPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComponentOrder{");
        sb.append("product=").append(product);
        sb.append(", orderedProductAmount=").append(orderedProductAmount);
        sb.append(", orderedProductFullPrice=").append(orderedProductFullPrice);
        sb.append('}');
        return sb.toString();
    }
}
