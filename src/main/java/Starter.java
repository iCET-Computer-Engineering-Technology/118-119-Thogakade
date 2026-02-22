import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }
}
