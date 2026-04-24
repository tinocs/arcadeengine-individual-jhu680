/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;

import engine.Actor;
import javafx.scene.image.Image;



public class Brick extends Actor{
	private String BRICK_IMAGE1_PATH = getClass().getClassLoader().getResource("breakoutresources/brick.png").toString();
	private String BRICK_IMAGE2_PATH = getClass().getClassLoader().getResource("breakoutresources/brick2.png").toString();
	
	public Brick() {
		setImage(new Image(BRICK_IMAGE1_PATH));
	}
	
	public Brick(int strength) {
		if(strength == 1) {
			setImage(new Image(BRICK_IMAGE1_PATH));
		}else {
			setImage(new Image(BRICK_IMAGE2_PATH));
		}
	}
	
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
	public void setStrength(int strength) {
		if(strength == 1) {
			setImage(new Image(BRICK_IMAGE1_PATH));
		}else {
			setImage(new Image(BRICK_IMAGE2_PATH));
		}
	}

}
