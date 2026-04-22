/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;

import engine.Actor;
import javafx.scene.image.Image;



public class Brick extends Actor{
	private String BRICK_IMAGE_PATH = getClass().getClassLoader().getResource("breakoutresources/brick.png").toString();
	
	public Brick() {
		setImage(new Image(BRICK_IMAGE_PATH));
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}
