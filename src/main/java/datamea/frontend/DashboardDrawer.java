package datamea.frontend;

import com.jfoenix.controls.JFXButton;
import datamea.backend.Main;
import datamea.backend.UserFolder;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardDrawer implements Initializable{

    @FXML
    public JFXListView listView;

    @FXML
    public VBox dashboardDrawerVBox;

    @FXML
    public JFXButton settingsButton;

    @FXML
    public ScrollPane drawerScrollpane;

    @FXML
    public AnchorPane drawerAnchorpane;

    //------------------Declaring Variables------------------//
    public static ObservableList<String> list = FXCollections.observableArrayList();
    private static BooleanProperty loadFolderList = new SimpleBooleanProperty(false);
    private static BooleanProperty expandList = new SimpleBooleanProperty(false);
    private static BooleanProperty shrinkList = new SimpleBooleanProperty(false);



    public static void setLoadFolderList(Boolean b){
        loadFolderList.setValue(b);
    }

    public static void setExpandListToTrue(){
        expandList.setValue(true);
    }

    public static void setShrinkListToTrue(){
        shrinkList.setValue(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){

        //list.add(0,"All");
        loadFolderList.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    for (UserFolder f : Main.getFolders()){
                        list.add(f.getFolderName());
                        for (String subFolder: f.subFolders) {
                            if (!subFolder.equals(f.getFolderName())) {
                                list.add("          " + subFolder);
                            }
                        }
                    }
                    listView.setItems(list);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            listView.scrollTo(list.get(0));
                            listView.getSelectionModel().select(list.get(0));
                        }
                    });
                }
            }
        });

        expandList.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    listView.setExpanded(true);
                    listView.setVerticalGap(20.0);
                    shrinkList.setValue(false);
                }
            }
        });

        shrinkList.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    listView.setExpanded(false);
                    listView.setVerticalGap(0.0);
                    expandList.setValue(false);
                }
            }
        });

    }
}
