package com.makkras.shop.entity;

import java.math.BigDecimal;

public class Product extends CustomEntity{
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private boolean isInStock;
    private String picturePath;
    private String productComment;
    private ProductCategory productCategory;
    public Product(){
    }
    public Product(Long productId){
        this.productId = productId;
    }
    public Product(Long productId, String productName, BigDecimal productPrice,
                   boolean isInStock, String picturePath, String productComment,
                   ProductCategory productCategory) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.isInStock = isInStock;
        this.picturePath = picturePath;
        this.productComment = productComment;
        this.productCategory = productCategory;
    }
    public Product(String productName, BigDecimal productPrice,
                   boolean isInStock, String picturePath, String productComment,
                   ProductCategory productCategory) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.isInStock = isInStock;
        this.picturePath = picturePath;
        this.productComment = productComment;
        this.productCategory = productCategory;
    }
    public Product(String productName,
                   boolean isInStock,
                   ProductCategory productCategory) {
        this.productName = productName;
        this.isInStock = isInStock;
        this.productCategory = productCategory;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStockStatus(boolean inStock) {
        isInStock = inStock;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getProductComment() {
        return productComment;
    }

    public void setProductComment(String productComment) {
        this.productComment = productComment;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (isInStock != product.isInStock) return false;
        if (productId != null ? !productId.equals(product.productId) : product.productId != null) return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        if (productPrice != null ? !productPrice.equals(product.productPrice) : product.productPrice != null)
            return false;
        if (picturePath != null ? !picturePath.equals(product.picturePath) : product.picturePath != null) return false;
        if (productComment != null ? !productComment.equals(product.productComment) : product.productComment != null)
            return false;
        return productCategory != null ? productCategory.equals(product.productCategory) : product.productCategory == null;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        result = 31 * result + (isInStock ? 1 : 0);
        result = 31 * result + (picturePath != null ? picturePath.hashCode() : 0);
        result = 31 * result + (productComment != null ? productComment.hashCode() : 0);
        result = 31 * result + (productCategory != null ? productCategory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("productId=").append(productId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", productPrice=").append(productPrice);
        sb.append(", isInStock=").append(isInStock);
        sb.append(", picturePath='").append(picturePath).append('\'');
        sb.append(", productComment='").append(productComment).append('\'');
        sb.append(", productCategory=").append(productCategory);
        sb.append('}');
        return sb.toString();
    }
}
