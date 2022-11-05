package edu.bsuir.controllers;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import edu.bsuir.client.Client;
import edu.bsuir.entities.Stock;
import edu.bsuir.entities.StockProperty;
import edu.bsuir.jsonprocessing.JsonStringProcessing;
import edu.bsuir.jsonprocessing.StockJsonStringProcessingImpl;
import edu.bsuir.model.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;


public class WorkWithStocksController {

    private static final String ADDNEWSTOCK = "ADDNEWSTOCK";
    private static final String DELETESTOCK = "DELETESTOCK";
    private static final String UPDATESTOCK = "UPDATESTOCK ";
    private static final String VIEWALLSTOCKS = "VIEWALLSTOCKS";

    Client client = Client.getInstance();
    JsonStringProcessing stockJsonProcessingImpl = new StockJsonStringProcessingImpl();

    @FXML
    private Button add_new_stock;

    @FXML
    private Button delete_stock;

    @FXML
    private TextField name_field;

    @FXML
    private TextField size_field;

    @FXML
    private Button update_stock;

    @FXML
    private Button view_stocks;


    @FXML
    private Button back;

    @FXML
    private Button work_with_product_button;

    @FXML
    private TableView<StockProperty> table_for_stocks;

    @FXML
    private TableColumn<StockProperty, String> column_name;

    @FXML
    private TableColumn<StockProperty, Integer> column_size;

    private final ObservableList<StockProperty> table_for_stocks_properties = FXCollections.observableArrayList();

    public WorkWithStocksController() throws IOException {
    }

    public void initialize(){

        add_new_stock.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                String name = name_field.getText();
                int size = Integer.parseInt(size_field.getText());
                Stock stock = new Stock(name,size);
                String jsonStock = stockJsonProcessingImpl.stringSerialisation(stock);
                String typeOfOperation = ADDNEWSTOCK;
                System.out.println(client.dataSendAndTake(typeOfOperation, jsonStock));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        delete_stock.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                String name = name_field.getText();
                int size = Integer.parseInt(size_field.getText());
                Stock stock = new Stock(name,size);
                String jsonStock = stockJsonProcessingImpl.stringSerialisation(stock);
                String typeOfOperation = DELETESTOCK;
                System.out.println(client.dataSendAndTake(typeOfOperation, jsonStock));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        update_stock.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                String name = name_field.getText();
                int size = Integer.parseInt(size_field.getText());
                Stock stock = new Stock(name,size);
                String jsonStock = stockJsonProcessingImpl.stringSerialisation(stock);
                String typeOfOperation = UPDATESTOCK;
                System.out.println(client.dataSendAndTake(typeOfOperation, jsonStock));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        view_stocks.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                String typeOfOperation = VIEWALLSTOCKS;
                String jsonStringList = client.dataSendAndTake(typeOfOperation, null);
                ObjectMapper mapper = new ObjectMapper();
                TypeFactory factory = mapper.getTypeFactory();
                CollectionType listType =
                        factory.constructCollectionType(List.class, Stock.class);
                List <Stock> allstocks = mapper.readValue(jsonStringList, listType);
                table_for_stocks.setItems(table_for_stocks_properties);
                column_name.setCellValueFactory(cellValue -> cellValue.getValue().nameProperty());
                column_size.setCellValueFactory(cellValue -> cellValue.getValue().sizeProperty().asObject());
                table_for_stocks_properties.clear();
                for (int i = 0; i < allstocks.size(); i++) {
                    StockProperty e = new StockProperty(allstocks.get(i));
                    table_for_stocks_properties.add(e);
                }
                table_for_stocks.setItems(table_for_stocks_properties);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        work_with_product_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            App.initRootLayout("/workwithsproducts.fxml");
        });

        back.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            App.initRootLayout("/authorization.fxml");
        });

    }

}

