package salesfx;

import content.Employee;
import content.EmployeeFile;
import content.SearchStage;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import java.util.StringTokenizer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    private LinkedList<Employee> employeeList = new LinkedList<>();
    private TextField txtID = new TextField();
    private Label lblID = new Label("ID: ");
    private TextField txtName = new TextField();
    private Label lblName = new Label("Name: ");
    private TextField txtCity = new TextField();
    private Label lblCity = new Label("City: ");
    private TextField txtPos = new TextField();
    private Label lblPos = new Label("Position: ");
    private Button btnAdd = new Button("Add");
    private Button btnUpdate = new Button("Update");
    private Button btnDelete = new Button("Delete");
    private Button btnSearch = new Button("Search");
    private Button btnFirst = new Button("First");
    private Button btnNext = new Button("Next");
    private Button btnPrevious = new Button("Previous");
    private Button btnLast = new Button("Last");
    private HBox btns = new HBox(btnFirst, btnNext, btnPrevious, btnLast);
    int count = 0;

    @Override
    public void start(Stage stage) {

        btns.setPadding(new Insets(10));
        btnAdd.setOnAction(new AddEmployee());
        btnDelete.setOnAction(new DeleteEmployee());
        btnFirst.setOnAction(new FirstEmployee());
        btnLast.setOnAction(new LastEmployee());
        btnNext.setOnAction(new NextEmployee());
        btnPrevious.setOnAction(new PreviousEmployee());
        btnUpdate.setOnAction(new UpdateEmployee());
        stage.setOnCloseRequest(new EndProgram());

        try {
            EmployeeFile.setList(employeeList);
            Collections.sort(employeeList);
        } catch (Exception e) {

        }

        btnSearch.setOnAction(new SortEmployee());

        StringTokenizer tokenizer = new StringTokenizer(employeeList.getFirst().toString(), ",");
        txtID.setText(tokenizer.nextToken());
        txtName.setText(tokenizer.nextToken());
        txtCity.setText(tokenizer.nextToken());
        txtPos.setText(tokenizer.nextToken());
        Scene scene = new Scene(studentPane(), 400, 200);
        scene.getStylesheets().add("/css/styles.css");
        stage.setScene(scene);
        stage.show();

    }

    public class AddEmployee implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {

            btnAdd.setDisable(true);
            btnDelete.setDisable(true);
            txtID.clear();
            txtName.clear();
            txtCity.clear();
            txtPos.clear();
            txtID.requestFocus();

            btnUpdate.setOnAction((e) -> {

                boolean isValid = true;
                String id = txtID.getText();
                String name = txtName.getText();
                String city = txtCity.getText();
                String position = txtPos.getText().trim();

                try {
                    Integer.parseInt(id);
                } catch (NumberFormatException ex) {
                    isValid = false;
                }

                try {
                    Integer.parseInt(name);
                    isValid = false;
                } catch (Exception ex) {
                }

                try {
                    Integer.parseInt(city);
                    isValid = false;
                } catch (Exception ex) {
                }

                for (int i = 0; i < employeeList.size(); i++) {
                    if (id.equals(employeeList.get(i).getId())) {
                        isValid = false;
                    }
                }

                if ((position.equalsIgnoreCase("Worker") || position.equalsIgnoreCase("SalesPerson")) && isValid == true) {
                    Employee one = new Employee(id, name, city, position);

                    Alert dlgConfirm = new Alert(Alert.AlertType.CONFIRMATION);
                    Optional<ButtonType> result = dlgConfirm.showAndWait();
                    String message = new String();
                    if (result.get() == ButtonType.OK) {

                        message = "Changes Confirmed";
                        employeeList.add(employeeList.size(), one);
                        StringTokenizer tokenizer = new StringTokenizer(employeeList.getLast().toString(), ",");
                        txtID.setText(tokenizer.nextToken());
                        txtName.setText(tokenizer.nextToken());
                        txtCity.setText(tokenizer.nextToken());
                        txtPos.setText(tokenizer.nextToken());
                        btnAdd.setDisable(false);
                        btnDelete.setDisable(false);

                    } else {
                        message = "Changes Cancelled";
                        StringTokenizer tokenizer = new StringTokenizer(employeeList.getLast().toString(), ",");
                        txtID.setText(tokenizer.nextToken());
                        txtName.setText(tokenizer.nextToken());
                        txtCity.setText(tokenizer.nextToken());
                        txtPos.setText(tokenizer.nextToken());
                        btnAdd.setDisable(false);
                        btnDelete.setDisable(false);
                    }
                } else {
                    Alert dlgErr = new Alert(Alert.AlertType.ERROR);
                    dlgErr.setContentText("Information entered is Invalid");
                    dlgErr.show();
                    txtID.clear();
                    txtName.clear();
                    txtCity.clear();
                    txtPos.clear();
                    txtID.requestFocus();
                }

            });

        }

    }

    public class DeleteEmployee implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            Alert dlgConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = dlgConfirm.showAndWait();
            String message = new String();
            if (result.get() == ButtonType.OK) {
                message = "Changes Confirmed";

                employeeList.remove(count);
                if (count > employeeList.size() - 1) {
                    StringTokenizer tokenizer = new StringTokenizer(employeeList.getFirst().toString(), ",");
                    txtID.setText(tokenizer.nextToken());
                    txtName.setText(tokenizer.nextToken());
                    txtCity.setText(tokenizer.nextToken());
                    txtPos.setText(tokenizer.nextToken());
                    txtID.requestFocus();
                } else {
                    StringTokenizer tokenizer = new StringTokenizer(employeeList.get(count).toString(), ",");
                    txtID.setText(tokenizer.nextToken());
                    txtName.setText(tokenizer.nextToken());
                    txtCity.setText(tokenizer.nextToken());
                    txtPos.setText(tokenizer.nextToken());
                    txtID.requestFocus();
                }

            } else {
                message = "Changes Cancelled";
            }

        }

    }

    public class UpdateEmployee implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {

            boolean isValid = true;
            String id = txtID.getText();
            String name = txtName.getText();
            String city = txtCity.getText();
            String position = txtPos.getText();

            try {
                Integer.parseInt(id);
            } catch (NumberFormatException ex) {
                isValid = false;
            }

            try {
                Integer.parseInt(name);
                isValid = false;
            } catch (Exception ex) {
            }

            try {
                Integer.parseInt(city);
                isValid = false;
            } catch (Exception ex) {
            }

            if (isValid == true) {
                Alert dlgConfirm = new Alert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> result = dlgConfirm.showAndWait();
                String message = new String();

                if (result.get() == ButtonType.OK) {
                    message = "Changes Confirmed";
                    employeeList.remove(count);

                    Employee one = new Employee(id, name, city, position);
                    employeeList.add(count, one);
                    StringTokenizer tokenizer = new StringTokenizer(employeeList.get(count).toString(), ",");
                    txtID.setText(tokenizer.nextToken());
                    txtName.setText(tokenizer.nextToken());
                    txtCity.setText(tokenizer.nextToken());
                    txtPos.setText(tokenizer.nextToken());
                    txtID.requestFocus();


                } else {
                    message = "Changes Cancelled";
                    StringTokenizer tokenizer = new StringTokenizer(employeeList.get(count).toString(), ",");
                    txtID.setText(tokenizer.nextToken());
                    txtName.setText(tokenizer.nextToken());
                    txtCity.setText(tokenizer.nextToken());
                    txtPos.setText(tokenizer.nextToken());
                    txtID.requestFocus();
                }
                Alert dlgMessage = new Alert(Alert.AlertType.INFORMATION);
                dlgMessage.setContentText(message);
                dlgMessage.show();

            } else {
                Alert dlgErr = new Alert(Alert.AlertType.ERROR);
                dlgErr.setContentText("Information entered is Invalid");
                dlgErr.show();
                txtID.requestFocus();
            }

        }

    }

    public class FirstEmployee implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {

            StringTokenizer tokenizer = new StringTokenizer(employeeList.getFirst().toString(), ",");
            txtID.setText(tokenizer.nextToken());
            txtName.setText(tokenizer.nextToken());
            txtCity.setText(tokenizer.nextToken());
            txtPos.setText(tokenizer.nextToken());
            count = 0;
        }

    }

    public class LastEmployee implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {

            StringTokenizer tokenizer = new StringTokenizer(employeeList.getLast().toString(), ",");
            txtID.setText(tokenizer.nextToken());
            txtName.setText(tokenizer.nextToken());
            txtCity.setText(tokenizer.nextToken());
            txtPos.setText(tokenizer.nextToken());
            count = employeeList.size() - 1;
        }

    }

    public class NextEmployee implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {

            if (count + 1 < employeeList.size()) {
                StringTokenizer tokenizer = new StringTokenizer(employeeList.get(count + 1).toString(), ",");
                txtID.setText(tokenizer.nextToken());
                txtName.setText(tokenizer.nextToken());
                txtCity.setText(tokenizer.nextToken());
                txtPos.setText(tokenizer.nextToken());
                count++;
            }

        }

    }

    public class PreviousEmployee implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {

            if (count - 1 >= 0) {
                StringTokenizer tokenizer = new StringTokenizer(employeeList.get(count - 1).toString(), ",");
                txtID.setText(tokenizer.nextToken());
                txtName.setText(tokenizer.nextToken());
                txtCity.setText(tokenizer.nextToken());
                txtPos.setText(tokenizer.nextToken());
                count--;
            }

        }

    }

    public class EndProgram implements EventHandler<WindowEvent> {

        @Override
        public void handle(WindowEvent t) {

            try {
                EmployeeFile.saveStatus(employeeList);

            } catch (IOException e) {
                Alert dlgErr = new Alert(Alert.AlertType.ERROR);
                dlgErr.setHeaderText("Data not saved");
                dlgErr.setContentText(e.toString());
                dlgErr.show();
            }
            
            Alert dlgInfo = new Alert(Alert.AlertType.INFORMATION);
            dlgInfo.setContentText("Data saved - Program Ending");
            dlgInfo.show();
        }

    }

    public class SortEmployee implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
//            Collections.sort(employeeList);
            SearchStage stage = new SearchStage(employeeList);
            stage.show();
        }

    }

    private GridPane studentPane() {
        GridPane pane = new GridPane();
        pane.add(lblID, 0, 0);
        pane.add(txtID, 1, 0);
        pane.add(btnAdd, 2, 0);
        pane.add(lblName, 0, 1);
        pane.add(txtName, 1, 1);
        pane.add(btnUpdate, 2, 1);
        pane.add(lblCity, 0, 2);
        pane.add(txtCity, 1, 2);
        pane.add(btnDelete, 2, 2);
        pane.add(lblPos, 0, 3);
        pane.add(txtPos, 1, 3);
        pane.add(btnSearch, 2, 3);
        pane.add(btns, 1, 4);

        return pane;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
