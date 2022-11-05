package edu.bsuir.serverthread;

import edu.bsuir.entities.*;
import edu.bsuir.jsonprocessing.*;
import edu.bsuir.services.*;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerThread extends Thread{

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    private static final String VIEWALLTASKS = "VIEWALLTASKS";
    private static final String ADDNEWPRODUCT = "ADDNEWPRODUCT";
    private static final String DELETEPRODUCT = "DELETEPRODUCT";
    private static final String UPDATEPRODUCT = "UPDATEPRODUCT";
    private static final String VIEWALLPRODUCTS = "VIEWALLPRODUCTS";
    private static final String ADDNEWSTOCK = "ADDNEWSTOCK";
    private static final String DELETESTOCK = "DELETESTOCK";
    private static final String UPDATESTOCK = "UPDATESTOCK ";
    private static final String VIEWALLSTOCKS = "VIEWALLSTOCKS";
    private static final String ADDNEWTASK = "ADDNEWTASK";
    private static final String DELETETASK = "DELETETASK";
    private static final String UPDATETASK = "UPDATETASK";
    private static final String ZAGLUSHKA = "OK";


    JsonStringProcessing productJsonStringProcessing = new ProductJsonStringProcessingImpl();
    JsonStringProcessing stockJsonStringProcessing = new StockJsonStringProcessingImpl();
    JsonStringProcessing taskJsonStringProcessing = new TaskJsonProcessingImpl();

    Service productService = new ProductServiceImpl();
    Service stockService = new StockServiceImpl();
    Service taskService = new TaskServiceImpl();


    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {

        try {
            while (true) {
                String typeOfOperation = in.readLine();
                String jsonStringObject = in.readLine();
                switch (typeOfOperation){
                    case ADDNEWPRODUCT:
                        Product newproduct = (Product) productJsonStringProcessing.stringDeserialisation(jsonStringObject);
                        List<Stock> viewStockList1 = stockService.findAllEntities();
                        for (Stock stock: viewStockList1){
                            if(stock.getName().equals("teststock")){
                                newproduct.setStock(stock);
                            }
                        }
                        productService.saveEntity(newproduct);
                        send(ZAGLUSHKA);
                        break;
                    case DELETEPRODUCT:
                        Product deletedproduct = (Product) productJsonStringProcessing.stringDeserialisation(jsonStringObject);
                        List<Product> deletedList = productService.findAllEntities();
                        for (Product product: deletedList){
                            if((product.getName().equals(deletedproduct.getName()))){
                                productService.deleteEntity(product);
                            }
                        }
                        send(ZAGLUSHKA);
                        break;
                    case UPDATEPRODUCT:
                        Product updatedproduct = (Product) productJsonStringProcessing.stringDeserialisation(jsonStringObject);
                        List<Product> updatedList = productService.findAllEntities();
                        List<Stock> viewStockList2 = stockService.findAllEntities();
                        for (Stock stock: viewStockList2){
                            if(stock.getName().equals("teststock")){
                                updatedproduct.setStock(stock);
                            }
                        }
                        for (Product product: updatedList){
                            if(product.getName().equals(updatedproduct.getName())){
                                productService.deleteEntity(product);
                                productService.saveEntity(updatedproduct);
                            }
                        }
                        send(ZAGLUSHKA);
                        break;
                    case VIEWALLPRODUCTS:
                        List<Product> viewProductList = productService.findAllEntities();
                        String jsonViewProductList = productJsonStringProcessing.stringListSerialisation(viewProductList);
                        send(jsonViewProductList);
                        break;
                    case ADDNEWSTOCK:
                        Stock newstock = (Stock) stockJsonStringProcessing.stringDeserialisation(jsonStringObject);
                        stockService.saveEntity(newstock);
                        send(ZAGLUSHKA);
                        break;
                    case DELETESTOCK:
                        Stock deletedstock = (Stock) stockJsonStringProcessing.stringDeserialisation(jsonStringObject);
                        List<Stock> deletedStockList = stockService.findAllEntities();
                        for (Stock stock: deletedStockList){
                            if((stock.getName().equals(deletedstock.getName()))){
                                stockService.deleteEntity(stock);
                            }
                        }
                        send(ZAGLUSHKA);
                        break;
                    case UPDATESTOCK:
                        Stock updatedstock = (Stock) stockJsonStringProcessing.stringDeserialisation(jsonStringObject);
                        List<Stock> updatedStockList = stockService.findAllEntities();
                        for (Stock stock: updatedStockList){
                            if(stock.getName().equals(updatedstock.getName())){
                                stockService.deleteEntity(stock);
                                stockService.saveEntity(updatedstock);
                            }
                        }
                        send(ZAGLUSHKA);
                        break;
                    case VIEWALLSTOCKS:
                        List<Stock> viewStockList = stockService.findAllEntities();
                        String jsonViewStockList = stockJsonStringProcessing.stringListSerialisation(viewStockList);
                        send(jsonViewStockList);
                        break;
                    case ADDNEWTASK:
                        Task newtask = (Task) taskJsonStringProcessing.stringDeserialisation(jsonStringObject);
                        taskService.saveEntity(newtask);
                        send(ZAGLUSHKA);
                        break;
                    case DELETETASK:
                        Task deletedtask = (Task) taskJsonStringProcessing.stringDeserialisation(jsonStringObject);
                        List<Task> deletedTaskList = taskService.findAllEntities();
                        for (Task task: deletedTaskList){
                            if((task.getState().equals(deletedtask.getState()))){
                                taskService.deleteEntity(task);
                            }
                        }
                        send(ZAGLUSHKA);
                        break;
                    case UPDATETASK:
                        Task updatedtask = (Task) taskJsonStringProcessing.stringDeserialisation(jsonStringObject);
                        List<Task> updatedTaskList = taskService.findAllEntities();
                        for (Task task: updatedTaskList){
                            if(task.getState().equals(updatedtask.getState())){
                                taskService.deleteEntity(task);
                                taskService.saveEntity(updatedtask);
                            }
                        }
                        send(ZAGLUSHKA);
                        break;
                    case VIEWALLTASKS:
                        List<Task> viewTaskList = taskService.findAllEntities();
                        String jsonViewTaskList = taskJsonStringProcessing.stringListSerialisation(viewTaskList);
                        send(jsonViewTaskList);
                        break;

                }

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("Новая нить закончила свою работу,сокет общения закрыт, присоединитесь снова");
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send(String serverAnswer) {
        try {
            out.write(serverAnswer + "\n");
            out.flush();
        } catch (IOException ignored) {

        }
    }


}
