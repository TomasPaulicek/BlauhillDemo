package com.blauhill.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "properties")
public class Property {
	
  @Id
  private String id;
  private int GrLivArea;
  private int SalePrice;

  public Property() {

  }

  public Property(int GrLivArea, int SalePrice) {
    this.GrLivArea = GrLivArea;
    this.SalePrice = SalePrice;
  }

  public String getId() {
    return id;
  }
  
  public int getGrLivArea() {
    return GrLivArea;
  }

  public void setGrLivArea(int GrLivArea) {
    this.GrLivArea = GrLivArea;
  }
  
  public int getSalePrice() {
    return SalePrice;
  }

  public void setSalePrice(int SalePrice) {
    this.SalePrice = SalePrice;
  }

  @Override
  public String toString() {
    return "Property [id=" + id + ", GrLivArea=" + GrLivArea + ", SalePrice=" + SalePrice + "]";
  }
}
