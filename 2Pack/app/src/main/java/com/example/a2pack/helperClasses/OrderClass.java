package com.example.a2pack.helperClasses;

public class OrderClass {

   private String modelName;
   private String modelSize;
   private String perPiece;
   private String quantity;
   private String totalPrice;

   public OrderClass(){

   }

    public OrderClass(String modelName, String modelSize, String perPiece, String quantity, String totalPrice) {
        this.modelName = modelName;
        this.modelSize = modelSize;
        this.perPiece = perPiece;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelSize() {
        return modelSize;
    }

    public void setModelSize(String modelSize) {
        this.modelSize = modelSize;
    }

    public String getPerPiece() {
        return perPiece;
    }

    public void setPerPiece(String perPiece) {
        this.perPiece = perPiece;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
