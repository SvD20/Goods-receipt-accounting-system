package edu.bsuir.jsonprocessing;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bsuir.entities.Product;

import java.io.IOException;
import java.util.List;

public class ProductJsonStringProcessingImpl implements JsonStringProcessing<Product>{

    @Override
    public String stringSerialisation(Product product) throws JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonProduct = mapper.writeValueAsString(product);
        return jsonProduct;
    }

    @Override
    public Product stringDeserialisation(String jsonProduct) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(jsonProduct,Product.class);
        return product;
    }

    @Override
    public String stringListSerialisation(List<Product> list) throws JsonMappingException, IOException {
        return null;
    }
}
