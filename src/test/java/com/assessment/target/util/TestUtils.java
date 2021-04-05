package com.assessment.target.util;

import com.assessment.target.data.PriceInfo;
import com.assessment.target.dto.CurrentPrice;
import com.assessment.target.dto.DomainResponse;
import com.assessment.target.dto.FinalProduct;
import com.assessment.target.dto.Item;
import com.assessment.target.dto.Product;
import com.assessment.target.dto.ProductDescription;

public class TestUtils {

    public static DomainResponse getDomainResponse() {
        DomainResponse response = new DomainResponse();
        Product product = new Product();
        Item item = new Item();
        item.setTcin("123");
        ProductDescription productDescription = new ProductDescription();
        productDescription.setTitle("The Big Lebowski (Blu-ray) (Widescreen)");
        item.setProduct_description(productDescription);
        product.setItem(item);
        response.setProduct(product);
        return response;
    }

    public static CurrentPrice getCurrentPrice() {
        CurrentPrice currentPrice = new CurrentPrice();
        currentPrice.setValue(12.11);
        currentPrice.setCurrency_code("AUD");
        return currentPrice;
    }

    public static FinalProduct getFinalProduct(Integer id, CurrentPrice currentPrice) {
        FinalProduct finalProduct = new FinalProduct();
        finalProduct.setName("The Big Lebowski (Blu-ray) (Widescreen)");
        finalProduct.setId(id);
        finalProduct.setCurrent_price(currentPrice);
        return finalProduct;
    }

    public static PriceInfo getPriceInfo() {
        PriceInfo priceInfo = new PriceInfo();
        priceInfo.setId(11);
        priceInfo.setCurrent_price(getCurrentPrice());
        return priceInfo;
    }
}
