/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Breakout extends Application{
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Ball bounce!");
		
		
		BorderPane root = new BorderPane();
		BallWorld world = new BallWorld();
		root.setCenter(world);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
