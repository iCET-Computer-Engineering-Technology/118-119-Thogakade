package controller.item;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import model.tm.ItemTM;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

        tblItems.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            System.out.println("New value"+newValue);
            assert newValue != null;
            setTextToValues((ItemTM) newValue);
        });

    }

    private void setTextToValues(ItemTM itemTM){
        txtCode.setText(itemTM.getCode());
        txtDescription.setText(itemTM.getDescription());
        txtStock.setText(itemTM.getStock().toString());
        txtPackSize.setText(itemTM.getPackSize());
        txtUnitPrice.setText(itemTM.getUnitPrice().toString());
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
        ItemServiceImpl itemService = new ItemServiceImpl();
        List<Item> all = itemService.getAll();

        ArrayList<ItemTM> itemTMArrayList = new ArrayList<>();
        all.forEach(item -> {
            itemTMArrayList.add(new ItemTM(
                    item.getCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getStock()
            ));
        });
        tblItems.setItems(FXCollections.observableArrayList(itemTMArrayList));

    }


}
