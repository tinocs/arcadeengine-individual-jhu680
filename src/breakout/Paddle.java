/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;
import engine.Actor;
import javafx.scene.image.Image;


public class Paddle extends Actor{
	
	private final String PADDLE_IMAGE_PATH = getClass().getClassLoader().getResource("breakoutresources/paddle.png").toString();
	
	
	public Paddle() {
		setImage(new Image(PADDLE_IMAGE_PATH));
	}
	
	@Override
	public void act(long now) {
		if(getX()<=0||getX()+getWidth()>=getWorld().getWidth()) {
			if(getX()<=0) {
				move(-getX(),0);
			}else {
				move(-(getX()+getWidth()-getWorld().getWidth()),0);
			}
			
		}
	}

}
