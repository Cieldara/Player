
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.HBox;

public class Main extends Application {
    boolean visible = false;
    ToggleButton buttonList;
    BorderPane root;
    
    public void start(Stage stage) {

        //Le BorderPane à la racine de notre scène
        root = new BorderPane();

        //Le BorderPane du lecteur
        BorderPane playerPane = new BorderPane();

        //BorderPane pour les boutons de lecture
        BorderPane leftPane = new BorderPane();
        HBox upButtons = new HBox();
        Button prec = new Button("<<");
        Button play = new Button(">");
        Button next = new Button(">>");
        upButtons.getChildren().addAll(prec, play, next);

        HBox downButtons = new HBox();
        Button downPrec = new Button("|<");
        Button downPlay = new Button("||");
        Button downNext = new Button(">|");
        downButtons.getChildren().addAll(downPrec, downPlay, downNext);

        leftPane.setTop(upButtons);
        leftPane.setBottom(downButtons);

        //Le BorderPane pour les items à droite
        BorderPane centerPane = new BorderPane();

        BorderPane downSliderAndButtons = new BorderPane();
        Slider soundSlider = new Slider();
        
        HBox rightButtons = new HBox();
        Button buttonBars = new Button("|||");
        buttonList = new ToggleButton(":=");
        rightButtons.getChildren().addAll(buttonBars, buttonList);
        downSliderAndButtons.setLeft(soundSlider);
        downSliderAndButtons.setRight(rightButtons);

        BorderPane upPaneAndSlider = new BorderPane();

        Slider videoSlider = new Slider();
        BorderPane nameAndTime = new BorderPane();
        Label name = new Label("Lecteur multimédia VLC");
        Label time = new Label("00:00");
        nameAndTime.setLeft(name);
        nameAndTime.setRight(time);
        upPaneAndSlider.setTop(nameAndTime);
        upPaneAndSlider.setBottom(videoSlider);

        centerPane.setTop(upPaneAndSlider);
        centerPane.setBottom(downSliderAndButtons);

        playerPane.setLeft(leftPane);
        playerPane.setCenter(centerPane);

        
        BorderPane listPane = new BorderPane();

        BorderPane paneListDown = new BorderPane();
        HBox listButtons = new HBox();
        Button listPlus = new Button("+");
        Button listArrow = new Button("->");
        Button listLoop = new Button("-><-");
        listButtons.getChildren().addAll(listPlus, listArrow, listLoop);
        paneListDown.setLeft(listButtons);
        
        paneListDown.setCenter(new Label("X élements"));

        TextField textfield = new TextField();
        paneListDown.setRight(textfield);

        //TreeTableView peuplé avec quelques données
        TreeTableView<Music> tree = new TreeTableView<>();
        TreeTableColumn<Music,String> nom = new TreeTableColumn<>("Nom");
        TreeTableColumn<Music,String> auteur = new TreeTableColumn<>("Auteur");
        TreeTableColumn<Music,String> duree = new TreeTableColumn<>("Duree");
        TreeItem<Music> rootItem = new TreeItem<>();
        tree.setRoot(rootItem);
        //CellFactory pour les colonnes
        nom.setCellValueFactory(new TreeItemPropertyValueFactory<>("auteur"));
        auteur.setCellValueFactory(new TreeItemPropertyValueFactory<>("titre"));
        duree.setCellValueFactory(new TreeItemPropertyValueFactory<>("duree"));
      
        Music m = new Music("ACDCu", "H", "42");
        TreeItem<Music> t2 = new TreeItem<>(m);
        rootItem.getChildren().add(t2);
        tree.getColumns().addAll(nom,auteur,duree);
        listPane.setCenter(tree);
        listPane.setBottom(paneListDown);
        listPane.setVisible(visible);
        stage.setHeight(100);
        
        root.setTop(playerPane);
        root.setCenter(listPane);
        

        buttonList.setOnAction((ActionEvent event) -> {
            visible = !visible;
            if(!visible){
                stage.setHeight(100);
                buttonList.setStyle("");
            }else{
                stage.setHeight(400);
                buttonList.setStyle("-fx-base: cyan");
            }
            root.requestFocus();
            listPane.setVisible(visible);
        });

        stage.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() < 150){
                visible = false;
                stage.setHeight(100);
                buttonList.setSelected(false);
                buttonList.setStyle("");
                
            } else{
                visible = true;
                buttonList.setSelected(true);
                buttonList.setStyle("-fx-base: cyan");
            }
            root.requestFocus();
            listPane.setVisible(visible);
        });
        
        stage.setTitle("Player");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
