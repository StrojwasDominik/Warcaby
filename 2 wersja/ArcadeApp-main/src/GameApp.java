import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApp extends Application{
	@Override
	public void start(Stage primaryStage){
		startCheckers();
	}

	public void startCheckers(){
		CheckersGame game = new CheckersGame();
		Stage stage = game.getStage();
		Scene scene = new Scene(game.getBoard());

		stage.setMaxWidth(500);
		stage.setMaxHeight(500);
		stage.setTitle("Checkers");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public static void main(String[] args) {
		try {
			Application.launch(args);
		} catch (UnsupportedOperationException e) {
			System.exit(1);
		}
	}
}
