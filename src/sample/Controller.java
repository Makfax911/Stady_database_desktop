package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField input_id;

    @FXML
    private Button button_id;

    @FXML
    private VBox output_id;

    @FXML
    private Button delete_id;

    DatabaseHandler db=null;

    @FXML
    void initialize() {
         db=new DatabaseHandler();

         delete_id.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent event ) {

                 try {
                     db.delete_bd("0");
                     output_id.getChildren().clear();
                     ArrayList<String> tasks =db.getTasks();
                     for (int i=0;i<tasks.size();i++)
                         output_id.getChildren().add(new Label(tasks.get(i)));
                 } catch (SQLException throwables) {
                     throwables.printStackTrace();
                 } catch (ClassNotFoundException e) {
                     e.printStackTrace();
                 }
             }
         });

         button_id.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent mouseEvent) {

                 try {
                     // Проверяем является ли поле заполненным
                     if (!input_id.getText().trim().equals("")) {
                         // Вызываем метод из класса DB
                         // через этот метод будет добавлено новое задание
                         db.insertTask(input_id.getText());
                         loadInfo(); // Метод для подгрузки заданий внутрь программы
                         input_id.setText(""); // Очищаем поле

                     }
                 } catch (SQLException e) { // Отслеживаем ошибки
                     e.printStackTrace();
                 } catch (ClassNotFoundException e) {
                     e.printStackTrace();
                 }

             }
         });

    }
    private void loadInfo(){

        try {
            output_id.getChildren().clear();

            ArrayList<String> tasks =db.getTasks();
            for (int i=0;i<tasks.size();i++)
                output_id.getChildren().add(new Label(tasks.get(i)));
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}


