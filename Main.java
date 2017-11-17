
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.HBox;

public class Main extends Application {
    SimpleBooleanProperty visibleProperty = new SimpleBooleanProperty(false);

    public void start(Stage stage) {
        
        BorderPane root = new BorderPane();

        //Le BorderPane qui sera placé a la racine de notre scene
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
        
        BorderPane centerPane = new BorderPane();
        
        BorderPane downSliderAndButtons = new BorderPane();
        Slider soundSlider = new Slider();
        HBox rightButtons = new HBox();
        Button buttonBars = new Button("|||");
        ToggleButton buttonList = new ToggleButton(":=");
        buttonList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                visibleProperty.setValue(!visibleProperty.getValue());
            }
        });
        rightButtons.getChildren().addAll(buttonBars,buttonList);
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
        listPane.visibleProperty().bind(visibleProperty);
                
        
        
        BorderPane paneListDown = new BorderPane();
        HBox listButtons = new HBox();
        Button listPlus = new Button("+");
        Button listArrow = new Button("->");
        Button listLoop = new Button("-><-");
        listButtons.getChildren().addAll(listPlus,listArrow,listLoop);
        paneListDown.setLeft(listButtons);
        paneListDown.setRight(new TextField());
        
        listPane.setCenter(new TreeTableView());
        listPane.setBottom(paneListDown);
        
        
        root.setTop(playerPane);
        root.setCenter(listPane);
        

        stage.setTitle("Player");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
