/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;
import java.io.File;

import engine.Actor;
import javafx.scene.image.Image;


public class Ball extends Actor{
	private double dx;
	private double dy;
	
	public Ball() {
		setImage(new Image("file:src/breakoutresources/ball.png"));
		dx = Math.random()*4+3;
		dy = Math.random()*4+3;
	}
	
	
	@Override
	public void act(long now) {
		move(dx, dy);
		
		if(getX()<=0||getX()+getWidth()>getWorld().getWidth()) {
			dx *=-1;
			if(getX()<=0) {
				move(-getX(),0);
			}else {
				move(0,getX()-getWorld().getWidth());
			}
			
		}
		
		if(getY()==0||getY()+getHeight()>getWorld().getHeight()) {
			dx *=-1;
			if(getY()<=0) {
				move(-getY(),0);
			}else {
				move(0,getY()-getWorld().getHeight());
			}
		}
		
	}

}
