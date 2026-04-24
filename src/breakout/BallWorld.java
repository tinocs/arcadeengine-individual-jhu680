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


public class BallWorld extends World{
	private Paddle paddle;
	private Score score;
	private int level;
	private Level levelWorld;
	
	public BallWorld() {
		setPrefSize(640, 400);
	}
	
	public BallWorld(int level) {
		this();
		this.level = level;
		levelWorld = new Level(this, level);
	}
	
	
	@Override
	public void onDimensionsInitialized() {
		
		Ball ball = new Ball();
		ball.move(this.getWidth()/2, this.getHeight()/2);
		add(ball);
		
		paddle = new Paddle();
		paddle.move(this.getWidth()/2, this.getHeight()*4/5);
		add(paddle);
		
		try {
			levelWorld.load(new File("level1.txt"), this);
		} catch (Exception e) { System.out.println("jerer");}
		
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
		System.out.println();
		
		
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
		
	}

}
