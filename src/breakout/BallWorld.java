/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;
import engine.World;


public class BallWorld extends World{
	
	public BallWorld() {
		setPrefSize(500, 500);
	}
	
	@Override
	public void onDimensionsInitialized() {
		Ball ball = new Ball();
		ball.move(this.getWidth()/2, this.getHeight()/2);
		add(ball);
		
		Paddle paddle = new Paddle();
		paddle.move(this.getWidth()/2, this.getHeight()*2/5);
		add(paddle);
		start();
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}
