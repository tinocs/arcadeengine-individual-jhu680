/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;
import java.io.File;
import java.io.FileNotFoundException;

import engine.World;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class BallWorld extends World{
	private Paddle paddle;
	private Score score;
	private int level;
	private Level levelWorld;
	private Stage stage;
	private Breakout breakout;
	
	public BallWorld(Stage stage, Breakout b) {
		setPrefSize(640, 400);
		level = 1;
		levelWorld = new Level(this, level);
		this.stage = stage;
		breakout = b;
	}
	
	public BallWorld(int level, Stage stage, Breakout b) {
		this(stage, b);
		this.level = level;
		this.stage = stage;
		//levelWorld = new Level(this, level);
	}
	
	
	@Override
	public void onDimensionsInitialized() {
		Ball ball = new Ball();
		ball.move(this.getWidth()/2, this.getHeight()/2);
		add(ball);
		
		paddle = new Paddle();
		paddle.move(this.getWidth()/2, this.getHeight()*4/5);
		add(paddle);
		
		
		
		/*for(int i = 0;i<5;i++) {
			Brick brick = new Brick();
			brick.move(this.getWidth()/2-brick.getWidth()*5+brick.getWidth()*i, this.getHeight()*2/3);
			add(brick);
		}*/
		
		score = new Score();
		score.setX(this.getWidth()/2-score.getBoundsInParent().getWidth()/2);
		score.setY(score.getBoundsInParent().getHeight());
		getChildren().add(score);
		
		setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				paddle.move(event.getX()-paddle.getX()-paddle.getWidth()/2,0);
			}
		
		});
		
		start();
	}

	public Score getScore() {
		return score;
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		if(isKeyPressed(KeyCode.LEFT)) {
			paddle.move(-3,0);
		}else if(isKeyPressed(KeyCode.RIGHT)) {
			paddle.move(3, 0);
		}

		if(getObjects(Brick.class).size()==0) {
			level++;
			
			try {
				levelWorld.load(new File("level"+level+".xt"), this );
			} catch (Exception e) {
				breakout.setTitle();
				stop();
				score.setScoreVal(0);
				level = 0;
			}
			
		}
		
	}
	
	public void clear() {
		levelWorld.clear();
	}
}
