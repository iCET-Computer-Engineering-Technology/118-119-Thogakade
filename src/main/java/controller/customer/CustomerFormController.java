package controller.customer;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.tm.CustomerTM;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    @FXML
    private JFXComboBox cmbTitle;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colCity;

    @FXML
    private TableColumn colDob;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPostalCode;

    @FXML
    private TableColumn colProvince;

    @FXML
    private TableColumn colSalary;

    @FXML
    private DatePicker dateDob;

    @FXML
    private TableView tblCustomers;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtProvince;

    @FXML
    private JFXTextField txtSalary;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbTitle.setItems(
                FXCollections.observableArrayList(
                        Arrays.asList("Mr", "Miss", "Ms")
                )
        );

        loadTable();

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {

            assert t1 != null;

            CustomerTM customerTM = (CustomerTM) t1;

            Customer customer = new Customer(
                    customerTM.getId(),
                    customerTM.getName(),
                    customerTM.getName(),
                    customerTM.getDob(),
                    customerTM.getSalary(),
                    customerTM.getAddress(),
                    customerTM.getCity(),
                    customerTM.getProvince(),
                    customerTM.getPostalCode()
            );

            setTextToValues(customer);
        });
    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String title = cmbTitle.getValue().toString();
        LocalDate dobValue = dateDob.getValue();
        Double salary = Double.parseDouble(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();

        Customer customer = new Customer(id, name, title, dobValue, salary, address, city, province, postalCode);

        System.out.println(customer);

        if (new CustomerServiceImpl().addCustomer(customer)) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Added !").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer not Added !").show();
        }

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    public void loadTable() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        List<Customer> all = new CustomerServiceImpl().getAll();
        ArrayList<CustomerTM> customerTMArrayList = new ArrayList<>();

        all.forEach(customer -> {
            customerTMArrayList.add(new CustomerTM(
                    customer.getId(),
                    customer.getTitle(),
                    customer.getName(),
                    customer.getDobValue(),
                    customer.getSalary(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getProvince(),
                    customer.getPostalCode()
            ));
        });

        tblCustomers.setItems(FXCollections.observableArrayList(customerTMArrayList));


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if (new CustomerServiceImpl().deleteCustomer(txtId.getText())) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer NOT Deleted!").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        setTextToValues(new CustomerServiceImpl().searchCustomerById(txtId.getText()));
    }

    private void setTextToValues(Customer customer) {
        txtId.setText(customer.getId());
        cmbTitle.setValue(customer.getTitle());
        txtName.setText(customer.getName());
        dateDob.setValue(customer.getDobValue());
        txtSalary.setText(customer.getSalary().toString());
        txtAddress.setText(customer.getAddress());
        txtCity.setText(customer.getCity());
        txtProvince.setText(customer.getProvince());
        txtPostalCode.setText(customer.getPostalCode());
    }


}
