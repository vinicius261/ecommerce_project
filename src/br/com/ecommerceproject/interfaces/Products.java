package br.com.ecommerceproject.interfaces;

import java.sql.DataTruncation;

public interface Products {
    String getCode();
    String getName();
    Double getPrice();
    Integer getQuantity();
    void increaseQuantity();
    void decreaseQuantity();
    String getDescription();
}
