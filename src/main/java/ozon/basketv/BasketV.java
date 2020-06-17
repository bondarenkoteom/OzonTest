package ozon.basketv;

import io.qameta.allure.Attachment;

import java.util.ArrayList;

public class BasketV {
    private static ArrayList<Product> productList;

    private BasketV() {
    }

    @Attachment
    public static ArrayList<Product> getBasketV() {
        if (productList == null) {
            productList = new ArrayList<>();
        }
        return productList;
    }
}
