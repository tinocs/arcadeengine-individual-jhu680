/* Name: Jethro Hu
 * Date: Apr 22, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;

import engine.World;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TitleScreen extends World{
	private Button playButton;
	private BorderPane root;
	private Breakout b;
	private BallWorld ball;
	
	public TitleScreen(Breakout b, BallWorld ball) {
		this.ball = ball;
		setPrefSize(640, 400);
		playButton = new Button("New Game");
		root = new BorderPane();
		root.setPrefSize(640,400);
		this.b = b;
		
	}

	@Override
	public void onDimensionsInitialized() {
		Label title = new Label("BREAKOUT");
		BorderPane.setAlignment(title, Pos.CENTER);
		root.setPadding(new Insets(25,0,0,25));
		root.setTop(title);
		
		root.setCenter(playButton);
		getChildren().add(root);
		
		playButton.setOnAction(e -> {
			b.setLevel(1);
			ball.getLives().setLivesVal(3);
			ball.setGamePaused(true);
		});
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
}
