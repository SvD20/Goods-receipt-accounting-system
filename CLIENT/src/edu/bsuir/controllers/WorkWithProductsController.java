package edu.bsuir.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import edu.bsuir.client.Client;
import edu.bsuir.entities.Product;
import edu.bsuir.entities.ProductProperty;
import edu.bsuir.entities.Stock;
import edu.bsuir.entities.StockProperty;
import edu.bsuir.jsonprocessing.JsonStringProcessing;
import edu.bsuir.jsonprocessing.ProductJsonStringProcessingImpl;
import edu.bsuir.jsonprocessing.StockJsonStringProcessingImpl;
import edu.bsuir.model.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.List;

public class WorkWithProductsController {

    private static final String ADDNEWPRODUCT = "ADDNEWPRODUCT";
    private static final String DELETEPRODUCT = "DELETEPRODUCT";
    private static final String UPDATEPRODUCT = "UPDATEPRODUCT";
    private static final String VIEWALLPRODUCTS = "VIEWALLPRODUCTS";


    Client client = Client.getInstance();
    JsonStringProcessing productJsonProcessingImpl = new ProductJsonStringProcessingImpl();

    @FXML
    private Button add_new_product;

    @FXML
    private Button delete_product;

    @FXML
    private Button update_product;

    @FXML
    private Button view_products;

    @FXML
    private TextField name_field;

    @FXML
    private TextField count_field;

    @FXML
    private TextField state_field;

    @FXML
    private Button back;

    @FXML
    private Button work_with_tasks_button;

    @FXML
    private TableView<ProductProperty> table_for_products;

    @FXML
    private TableColumn<ProductProperty, String> column_name;

    @FXML
    private TableColumn<ProductProperty, Integer> column_count;

    @FXML
    private TableColumn<ProductProperty, String> column_state;

    private final ObservableList<ProductProperty> table_for_products_properties = FXCollections.observableArrayList();

    public WorkWithProductsController() throws IOException {
    }

    public void initialize(){

        add_new_product.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                String name = name_field.getText();
                int count = Integer.parseInt(count_field.getText());
                String state = state_field.getText();
                Product product = new Product(name,count,state);
                String jsonProduct = productJsonProcessingImpl.stringSerialisation(product);
                String typeOfOperation = ADDNEWPRODUCT;
                String serverAnswer = client.dataSendAndTake(typeOfOperation, jsonProduct);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        delete_product.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                String name = name_field.getText();
                int count = Integer.parseInt(count_field.getText());
                String state = state_field.getText();
                Product product = new Product(name,count,state);
                String jsonProduct = productJsonProcessingImpl.stringSerialisation(product);
                String typeOfOperation = DELETEPRODUCT;
                String serverAnswer = client.dataSendAndTake(typeOfOperation, jsonProduct);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        update_product.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                String name = name_field.getText();
                int count = Integer.parseInt(count_field.getText());
                String state = state_field.getText();
                Product product = new Product(name,count,state);
                String jsonProduct = productJsonProcessingImpl.stringSerialisation(product);
                String typeOfOperation = UPDATEPRODUCT;
                String serverAnswer = client.dataSendAndTake(typeOfOperation, jsonProduct);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        view_products.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                String typeOfOperation = VIEWALLPRODUCTS;
                String jsonStringList = client.dataSendAndTake(typeOfOperation, null);
                ObjectMapper mapper = new ObjectMapper();
                TypeFactory factory = mapper.getTypeFactory();
                CollectionType listType =
                        factory.constructCollectionType(List.class, Product.class);
                List <Product> allproducts = mapper.readValue(jsonStringList, listType);
                table_for_products.setItems(table_for_products_properties);
                column_name.setCellValueFactory(cellValue -> cellValue.getValue().nameProperty());
                column_count.setCellValueFactory(cellValue -> cellValue.getValue().countProperty().asObject());
                column_state.setCellValueFactory(cellValue -> cellValue.getValue().stateProperty());
                table_for_products_properties.clear();
                for (int i = 0; i < allproducts.size(); i++) {
                    ProductProperty e = new ProductProperty(allproducts.get(i));
                    table_for_products_properties.add(e);
                }
                table_for_products.setItems(table_for_products_properties);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        work_with_tasks_button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            App.initRootLayout("/workwithtasks.fxml");
        });

        back.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            App.initRootLayout("/workwithstocks.fxml");
        });

    }

}

