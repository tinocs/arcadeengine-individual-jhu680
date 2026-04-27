/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;
import java.io.File;
import java.io.FileNotFoundException;

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
	private Scene titleScene;
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		level = -1;
		stage.setTitle("Ball bounce!");
		this.stage = stage;
		stage.setResizable(false);
		
		world = new BallWorld(stage, this);
		
		BorderPane root = new BorderPane();
		titleStage = new TitleScreen(this, world);
		root.setCenter(titleStage);
		
		
		//root.setCenter(world);
		
		levelStage= new Level(world);
		//root.setCenter(level);
		titleScene = new Scene(root);
		stage.setScene(titleScene);
		stage.show();
		
	}
	
	public void setLevel(int level) {
		this.level = level;
		try {
			world.clear();
			levelStage.load(new File("test.txt"), world);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BorderPane root = new BorderPane();
		world.clear();
		root.setCenter(world);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		//stage.show();
	}
	
	public void setTitle() {
		stage.setScene(titleScene);
		level = 0;
	}
}
