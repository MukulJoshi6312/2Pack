package com.example.a2pack.helperClasses;

public class productViewModelClass {

    private int productImage;
    private int id;

    private String productModel,productSize,productPricePerPiece,productPrice,productQty,addToCart;

    public  productViewModelClass(){

    }


    public productViewModelClass(int productImage, String productModel, String productSize, String productPricePerPiece, String productPrice, String productQty) {
        this.productImage = productImage;
        this.productModel = productModel;
        this.productSize = productSize;
        this.productPricePerPiece = productPricePerPiece;
        this.productPrice = productPrice;
        this.productQty = productQty;

    }


    public productViewModelClass(String productModel, String productSize, String productPricePerPiece, String productPrice, String productQty) {
        this.productModel = productModel;
        this.productSize = productSize;
        this.productPricePerPiece = productPricePerPiece;
        this.productPrice = productPrice;
        this.productQty = productQty;

    }

    public productViewModelClass(String productModel, String productSize, String productPricePerPiece, String productPrice, String productQty,int id) {
        this.productModel = productModel;
        this.productSize = productSize;
        this.productPricePerPiece = productPricePerPiece;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.id = id;


    }



    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductPricePerPiece() {
        return productPricePerPiece;
    }

    public void setProductPricePerPiece(String productPricePerPiece) {
        this.productPricePerPiece = productPricePerPiece;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQty() {
        return productQty;
    }

    public void setProductQty(String productQty) {
        this.productQty = productQty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
