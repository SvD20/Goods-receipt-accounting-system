package edu.bsuir.jsonprocessing;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bsuir.entities.Task;

import java.io.IOException;
import java.util.List;

public class TaskJsonStringProcessingImpl implements JsonStringProcessing<Task>{
    @Override
    public String stringSerialisation(Task task) throws JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonTask = mapper.writeValueAsString(task);
        return jsonTask;
    }

    @Override
    public Task stringDeserialisation(String jsonTask) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Task task = mapper.readValue(jsonTask,Task.class);
        return task;
    }

    @Override
    public String stringListSerialisation(List<Task> list) throws JsonMappingException, IOException {
        return null;
    }
}
