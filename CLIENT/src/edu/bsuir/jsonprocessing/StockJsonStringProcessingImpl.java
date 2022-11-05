package edu.bsuir.jsonprocessing;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bsuir.entities.Stock;

import java.io.IOException;
import java.util.List;

public class StockJsonStringProcessingImpl implements JsonStringProcessing<Stock>{
    @Override
    public String stringSerialisation(Stock stock) throws JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStock = mapper.writeValueAsString(stock);
        return jsonStock;
    }

    @Override
    public Stock stringDeserialisation(String jsonStock) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Stock stock = mapper.readValue(jsonStock,Stock.class);
        return stock;
    }

    @Override
    public String stringListSerialisation(List<Stock> list) throws JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonList = mapper.writeValueAsString(list);
        return jsonList;
    }
}
