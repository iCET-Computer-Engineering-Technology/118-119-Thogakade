package controller.order;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import model.Order;
import model.OrderDetails;
import model.tm.CartTM;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ItemService;
import service.custom.OrderService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    public TableView tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colTotal;
    public TextField txtQtyOnHand;
    public Label lblNetTotal;
    public TextField txtOrderId;
    @FXML
    private ComboBox cmbItemIds;
    @FXML
    private Label lblItemName;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblStock;
    @FXML
    private Label lblUnitPrice;
    @FXML
    private Label lblName;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblPhone;
    @FXML
    private ComboBox cmbCustomerIds;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;


    CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
    ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);
    OrderService orderService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAndTime();
        loadCustomerIDs();
        loadItemCodes();
        cmbCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            assert newValue != null;
            setCustomerDataToLables((String) newValue);
        });

        cmbItemIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            assert newValue != null;
            setItemDataToLables((String) newValue);
        });


    }

    private void setItemDataToLables(String newValue) {
        try {
            Item itemByCode = itemService.getItemByCode(newValue);

            lblDescription.setText(itemByCode.getDescription());
            lblStock.setText(itemByCode.getStock().toString());
            lblUnitPrice.setText(itemByCode.getUnitPrice().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void setCustomerDataToLables(String id) {
        try {
            Customer customer = customerService.searchCustomerById(id);
            lblName.setText(customer.getName());
            lblAddress.setText(customer.getAddress());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadItemCodes() {
        try {
            List<String> itemCodes = itemService.getItemCodes();
            cmbItemIds.setItems(FXCollections.observableArrayList(itemCodes));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerIDs() {
        try {
            List<String> allCustomerIDs = customerService.getAllCustomerIDs();
            cmbCustomerIds.setItems(FXCollections.observableArrayList(allCustomerIDs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        lblDate.setText(sdf.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    ArrayList<CartTM> cartTMArrayList = new ArrayList<>();

    public void btnAddToCartOnAction(ActionEvent actionEvent) {

        cartTMArrayList.add(new CartTM(
                cmbItemIds.getValue().toString(),
                txtOrderId.getText(),
                lblDescription.getText(),
                Double.parseDouble(lblUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),
                Double.parseDouble(lblUnitPrice.getText()) * Integer.parseInt(txtQtyOnHand.getText())
        ));

        tblCart.setItems(FXCollections.observableArrayList(cartTMArrayList));
        calNetTotal();
    }

    private void calNetTotal(){
        Double total = 0.0;
        for(CartTM cartTM : cartTMArrayList){
            total+=cartTM.getTotal();
        }
        lblNetTotal.setText(total.toString());
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        ArrayList<OrderDetails> orderDetailsArrayList = new ArrayList<>();

        cartTMArrayList.forEach(cartTM -> orderDetailsArrayList.add(new OrderDetails(
                cartTM.getOrderId(),
                cartTM.getCode(),
                cartTM.getQtyOnHand(),
                0.0
        )));

        Order order = new Order(
                txtOrderId.getText(),
                LocalDate.now(),
                cmbCustomerIds.getValue().toString(),
                orderDetailsArrayList
        );

        try {
            if (orderService.placeOrder(order)){
                new Alert(Alert.AlertType.INFORMATION,"Order Placed !").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Order Not Placed !").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }
}
