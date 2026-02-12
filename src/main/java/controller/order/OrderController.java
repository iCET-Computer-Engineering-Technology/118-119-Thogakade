package controller.order;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ItemService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
//            lblStock.setText(itemByCode.getStock().toString());
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

    private void loadItemCodes(){
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


}
