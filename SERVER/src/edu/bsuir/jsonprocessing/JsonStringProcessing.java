package edu.bsuir.jsonprocessing;

import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.IOException;
import java.util.List;

public interface JsonStringProcessing <T>{

    String stringSerialisation(T obj) throws JsonMappingException, IOException;

    T stringDeserialisation(String obj) throws IOException;

    public String stringListSerialisation(List<T> list) throws JsonMappingException, IOException;

}
