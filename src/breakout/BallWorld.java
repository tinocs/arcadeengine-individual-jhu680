/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;
import engine.World;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class BallWorld extends World{
	private Paddle paddle;
	private Score score;
	
	public BallWorld() {
		setPrefSize(500, 500);
	}
	
	@Override
	public void onDimensionsInitialized() {
		
		Ball ball = new Ball();
		ball.move(this.getWidth()/2, this.getHeight()/2);
		add(ball);
		
		paddle = new Paddle();
		paddle.move(this.getWidth()/2, this.getHeight()*4/5);
		add(paddle);
		
		for(int i = 0;i<5;i++) {
			Brick brick = new Brick();
			brick.move(this.getWidth()/2-brick.getWidth()*5+brick.getWidth()*i, this.getHeight()*2/3);
			add(brick);
		}
		
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
		
	}

}
