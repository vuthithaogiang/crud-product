package crud.product.product;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    public static ObservableList<Product> listProducts = FXCollections.observableArrayList();
    @FXML
    private TableView<Product> table_view;
    @FXML
    private TableColumn<Product, String> col_category;

    @FXML
    private TableColumn<Product, String> col_description;

    @FXML
    private TableColumn<Product, String> col_name;

    @FXML
    private TableColumn<Product, Float> col_price;

    @FXML
    private TableColumn<Product, Integer> col_quantity;
    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    @FXML
    private ComboBox<?> category;
    private String[] comboCategoryTest = {"Art", "Clothes", "Digital", "Image", "Other"};


    public void comboBox() {
        List<String> list =  new ArrayList<>();
        for(String data : comboCategoryTest){
           list.add(data);
        }
        ObservableList dataList = FXCollections.observableArrayList(list);
        category.setItems(dataList);
    }


    public void showProduct() {
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        table_view.setItems(listProducts);
    }
    @FXML
    public void addNew(ActionEvent event) {
        try{
            String name = txtName.getText();
            var price = Float.valueOf(txtPrice.getText());
            var quantity = Integer.valueOf(txtQuantity.getText());
            String cat = (String) category.getSelectionModel().getSelectedItem();
            String desc = txtDescription.getText();
            for(Product p : listProducts){
                if(p.getName().equals(name) && p.getCategory().equals(cat)){
                    throw new Exception("This product is existed!!!");
                }
                if(quantity < 0 ){
                    throw new Exception("This value of quantity must be > 0!!!");
                }
            }

            Product product = new Product(name, price, quantity, cat, desc);
            listProducts.add(product);
            table_view.setItems(listProducts);
            txtName.clear();
            txtPrice.clear();
            category.getSelectionModel().clearSelection();
            txtQuantity.clear();
            txtDescription.clear();

        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }

    }

    @FXML
    public void update(ActionEvent event){
        Product updateProduct = table_view.getSelectionModel().getSelectedItem();
        int index = findProductInList(listProducts, updateProduct);
        try{
            String name = txtName.getText();
            var price = Float.valueOf(txtPrice.getText());
            var quantity = Integer.valueOf(txtQuantity.getText());
            String cat = (String) category.getSelectionModel().getSelectedItem();
            String desc = txtDescription.getText();

            updateProduct.setName(name);
            updateProduct.setPrice(price);
            updateProduct.setQuantity(quantity);
            updateProduct.setCategory(cat);
            updateProduct.setDescription(desc);

            if(index != -1){
                listProducts.remove(listProducts.get(index));
                listProducts.add(index, updateProduct);
            }
            showProduct();
            txtName.clear();
            txtPrice.clear();
            txtQuantity.clear();
            txtDescription.clear();

        }
        catch (Exception e){

        }

    }
    @FXML
     public void delete(ActionEvent event) {
        Product updateProduct = table_view.getSelectionModel().getSelectedItem();
        listProducts.remove(updateProduct);
    }
    public void selectProduct() {
        Product product = table_view.getSelectionModel().getSelectedItem();

        int num = table_view.getSelectionModel().getSelectedIndex();
        if((num -1) < -1)
            return;
        txtName.setText(product.getName());
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtQuantity.setText(String.valueOf(product.getQuantity()));
        //category.getSelectionModel().select(Integer.parseInt(product.getCategory()));
        txtDescription.setText(product.getDescription());
    }

    public int findProductInList(ObservableList<Product> list, Product product) {
        for(var p: list){
            if(p.getName().compareTo(product.getName()) == 0
            && p.getCategory().compareTo(product.getCategory()) == 0
            ){
                return list.indexOf(p);
            }
        }
        return -1;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox();
        showProduct();
    }
}
