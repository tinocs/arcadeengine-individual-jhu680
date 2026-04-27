/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;
import java.io.File;

import engine.Actor;
import engine.Sound;
import javafx.animation.*;
import javafx.scene.image.Image;


public class Ball extends Actor{
	private double dx;
	private double dy;
	private final String BALL_IMAGE_PATH = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
	Sound sound;
	
	
	public Ball() {
		setImage(new Image(BALL_IMAGE_PATH));
		dx = (Math.random()*4+3);
		dy = -(Math.random()*4+3);
		sound = new Sound("breakoutresources/ball_bounce.wav");
	}
	
	public double[] getDisplacement() {
		return new double[] {dx,dy};
	}
	
	public void setDy(double dy) {
		this.dy = dy;
	}
	
	public void randomize() {
		dx = (Math.random()*4+3);
		dy = -(Math.random()*4+3);
	}
	
	@Override
	public void act(long now) {
		if(((BallWorld)getWorld()).isGamePaused()){
			
		}else {
			move(dx, dy);
			
			if(getX()<=0||getX()+getWidth()>=getWorld().getWidth()) {
				sound.play();
				dx *=-1;
				if(getX()<=0) {
					move(-getX(),0);
				}else {
					move(getX()-getWorld().getWidth(),0);
				}
				
			}
			
			if(getY()<=0||getY()+getHeight()>=getWorld().getHeight()) {
				dy *=-1;
				sound.play();
				if(getY()<=0) {
					move(0,-getY());
				}else {
					move(0,getY()-getWorld().getHeight());
					((BallWorld)getWorld()).getScore().setScoreVal(((BallWorld)getWorld()).getScore().getScoreVal()-1000);
					((BallWorld)getWorld()).getLives().setLivesVal(((BallWorld)getWorld()).getLives().getLivesVal()-1);
				}
			}
			if(this.getOneIntersectingObject(Paddle.class)!=null) {
				dy*=-1;
				sound.play();
			}
			Brick brick = this.getOneIntersectingObject(Brick.class);
			if(brick != null&&brick.getInteractable()) {
				sound.play();
				
				if(getX()+getWidth()/2>brick.getX()&&getX()+getWidth()/2<brick.getX()+brick.getWidth()) {
					dy*=-1;
					//dy+= Math.random()-0.5;
				}
				if(getY()+getHeight()/2>brick.getY()&&getY()+getHeight()/2<brick.getY()+brick.getWidth()) {
					dx*=-1;
					//dx+= Math.random()-0.5;
				}
				((BallWorld)getWorld()).getScore().setScoreVal(((BallWorld)getWorld()).getScore().getScoreVal()+100);
				brick.setStrength(brick.getStrength()-1);
				if(brick.getStrength() <= 0) {
					//getWorld().remove(brick);
					brick.playAnimation();
					brick.playSound();
				}
			}
			
		
		}
	}

}
