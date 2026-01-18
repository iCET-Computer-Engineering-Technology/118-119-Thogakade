package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.tm.ItemTM;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    public TableColumn colUnitPrice;
    public JFXTextField txtUnitPrice;
    public TableView tblItems;
    @FXML
    private TableColumn colCode;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colPackSize;

    @FXML
    private TableColumn colStock;

    @FXML
    private TableView tblCustomers;

    @FXML
    private JFXTextField txtCode;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtStock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    private void loadTable(){
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROm item");

            ArrayList<ItemTM> itemTMArrayList = new ArrayList<>();

            while (resultSet.next()){
                ItemTM itemTM = new ItemTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)
                );
                itemTMArrayList.add(itemTM);
            }

            tblItems.setItems(FXCollections.observableArrayList(itemTMArrayList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
