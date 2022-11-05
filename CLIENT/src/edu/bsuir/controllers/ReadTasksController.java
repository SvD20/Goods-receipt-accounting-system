package edu.bsuir.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import edu.bsuir.client.Client;
import edu.bsuir.entities.Task;
import edu.bsuir.entities.TaskProperty;
import edu.bsuir.jsonprocessing.JsonStringProcessing;
import edu.bsuir.jsonprocessing.TaskJsonStringProcessingImpl;
import edu.bsuir.model.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

public class ReadTasksController {

    private static final String VIEWALLTASKS = "VIEWALLTASKS";

    Client client = Client.getInstance();
    JsonStringProcessing taskJsonStringProcessingImpl = new TaskJsonStringProcessingImpl();

    @FXML
    private Button display_button;

    @FXML
    private Button back;

    @FXML
    private TableView<TaskProperty> table_for_tasks;

    @FXML
    private TableColumn<TaskProperty, String> column_date;

    @FXML
    private TableColumn<TaskProperty, String> column_type;

    @FXML
    private TableColumn<TaskProperty, String> column_state;

    private final ObservableList<TaskProperty> table_for_tasks_properties = FXCollections.observableArrayList();


    public ReadTasksController() throws IOException {
    }

    public void initialize(){

        display_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                String typeOfOperation = VIEWALLTASKS;
                String jsonStringList = client.dataSendAndTake(typeOfOperation, null);
                ObjectMapper mapper = new ObjectMapper();
                TypeFactory factory = mapper.getTypeFactory();
                CollectionType listType =
                        factory.constructCollectionType(List.class, Task.class);
                List <Task> alltasks = mapper.readValue(jsonStringList, listType);
                table_for_tasks.setItems(table_for_tasks_properties);
                column_date.setCellValueFactory(cellValue -> cellValue.getValue().dateProperty());
                column_type.setCellValueFactory(cellValue -> cellValue.getValue().typeProperty());
                column_state.setCellValueFactory(cellValue -> cellValue.getValue().stateProperty());
                table_for_tasks_properties.clear();
                for (int i = 0; i < alltasks.size(); i++) {
                    TaskProperty e = new TaskProperty(alltasks.get(i));
                    table_for_tasks_properties.add(e);
                }
                table_for_tasks.setItems(table_for_tasks_properties);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        back.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            App.initRootLayout("/authorization.fxml");
        });

    }

}
