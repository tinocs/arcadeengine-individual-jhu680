/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;

import engine.Actor;
import engine.Sound;
import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.util.Duration;



public class Brick extends Actor{
	private String BRICK_IMAGE1_PATH = getClass().getClassLoader().getResource("breakoutresources/brick.png").toString();
	private String BRICK_IMAGE2_PATH = getClass().getClassLoader().getResource("breakoutresources/brick2.png").toString();
	private int strength;
	private Sound sound;
	private boolean interactable;
	private ParallelTransition animation;
	
	public Brick() {
		this(1);
	}
	
	public Brick(int strength) {
		interactable = true;
		if(strength == 1) {
			setImage(new Image(BRICK_IMAGE1_PATH));
		}else {
			setImage(new Image(BRICK_IMAGE2_PATH));
		}
		this.strength = strength;
		sound = new Sound("breakoutresources/brick_hit.wav");
		FadeTransition fade = new FadeTransition(Duration.millis(100),this);
		fade.setFromValue(1.0);
		fade.setToValue(0.1);
		
		ScaleTransition scale = new ScaleTransition(Duration.millis(100),this);
		scale.setByX(0.5);
		scale.setByY(0.5);
		scale.setByZ(0.5);
		
		animation = new ParallelTransition(this, fade, scale);
		animation.setOnFinished(e -> getWorld().remove(this));
	}
	
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
	public void setStrength(int strength) {
		if(strength == 1) {
			setImage(new Image(BRICK_IMAGE1_PATH));
		}else if(strength == 2){
			setImage(new Image(BRICK_IMAGE2_PATH));
		}
		this.strength = strength;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public void playSound() {
		sound.play();
	}
	
	public boolean getInteractable() {
		return interactable;
	}
	
	public void playAnimation() {
		interactable = false;
		animation.play();
		
		
	}

}
