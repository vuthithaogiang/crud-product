module crud.product.product {
    requires javafx.controls;
    requires javafx.fxml;


    opens crud.product.product to javafx.fxml;
    exports crud.product.product;
}