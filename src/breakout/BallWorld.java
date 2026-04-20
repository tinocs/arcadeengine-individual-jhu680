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
		
		setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				paddle.move(event.getX()-paddle.getX()-paddle.getWidth()/2,0);
			}
		
		});
		
		
		
		start();
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
