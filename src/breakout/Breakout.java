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
	private int level;
	private Level levelStage;
	private Stage stage;
	private TitleScreen titleStage;
	private BallWorld world;
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		level = 0;
		stage.setTitle("Ball bounce!");
		this.stage = stage;
		stage.setResizable(false);
		
		
		BorderPane root = new BorderPane();
		titleStage = new TitleScreen(this);
		root.setCenter(titleStage);
		
		world = new BallWorld();
		//root.setCenter(world);
		
		Level level = new Level(world);
		//root.setCenter(level);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
	}
	
	public void setLevel(int level) {
		this.level = level;
		levelStage = new Level(world, level);
		BorderPane root = new BorderPane();
		BallWorld world = new BallWorld();
		root.setCenter(world);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
