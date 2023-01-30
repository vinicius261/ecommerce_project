package br.com.ecommerceproject.interfaces;

import java.util.Date;

public interface Perishable extends Products {
    Date getBestBeforeDate();
}
