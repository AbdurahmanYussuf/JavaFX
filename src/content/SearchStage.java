/**
 * Abdurahman Yussuf 991625727
 * Mohammad Nasif Parvez 991582482
 * Final Project
 * April 18, 2021
 */

package content;

import java.util.Iterator;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SearchStage extends Stage {

    private RadioButton position = new RadioButton("Position");
    private RadioButton city = new RadioButton("City");
    private Button btnSearch = new Button("Search");
    private TextArea txtDisplay = new TextArea();
    private TextField txtSearch = new TextField();
    private Scene scene = new Scene(setDisplay(), 400, 250);
    private LinkedList<Employee> employeeList = new LinkedList();
    String display = new String();

    public SearchStage(LinkedList<Employee> employeeList) {
        this.employeeList = employeeList;
        btnSearch.setOnAction(new SearchEmployee());
        scene.getStylesheets().add("/css/styles.css");
        setScene(scene);
    }



    private BorderPane setDisplay() {
        BorderPane pane = new BorderPane();
        ToggleGroup group = new ToggleGroup();
        position.setToggleGroup(group);
        city.setToggleGroup(group);
        HBox pRadio = new HBox(position, city);

        HBox sPane = new HBox(txtSearch, btnSearch);
        pane.setTop(pRadio);
        pane.setCenter(txtDisplay);
        pane.setBottom(sPane);

        return pane;
    }

    private class SearchEmployee implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
            
            display = "";
            txtDisplay.clear();
            
            String filter = txtSearch.getText();
            Iterator<Employee> itEmployee = employeeList.iterator();
           
            while (itEmployee.hasNext()) {
                Employee one = itEmployee.next();
                if (position.isSelected()) {
                    if (filter.equalsIgnoreCase(one.getEmployee().trim())) {
                        display += one.getId()+ "\t\t" + one.getName() + "\t\t\t" + one.getCity() + "\t\t" + one.getEmployee() + "\n";
                        
                    }
                            
                }
                if(city.isSelected()){
                    if (filter.equalsIgnoreCase(one.getCity().trim())) {
                        display += one.getId()+ "\t\t" + one.getName() + "\t\t\t" + one.getCity() + "\t\t" + one.getEmployee() + "\n";
                        
                    }
                }
            }
            txtDisplay.setText(display);
           

            

        }

    }

}
