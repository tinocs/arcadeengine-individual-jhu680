/* Name: Jethro Hu
 * Date: Apr 7, 2026
 * Period: 1
 * Does it work: yes
 */

package engine;

import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

public abstract class Actor extends ImageView{
	
	public Actor() {
		
	}
	
	public void move(double dx, double dy) {
		this.setX(this.getX()+dx);
		this.setY(this.getY()+dy);
	}
	
	public World getWorld() {
		return (World)(this.getParent());
	}
	
	public double getWidth() {
		return this.getBoundsInParent().getWidth();
	}
	
	public double getHeight() {
		return this.getBoundsInParent().getHeight();
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
		List<A> objects = ((World)(this.getParent())).getObjects(cls);
		ArrayList<A> intersecting = new ArrayList<>();
		for(A actor:objects) {
			if(!(actor == this)&&actor.getBoundsInParent().intersects(this.getBoundsInParent())) {
				intersecting.add(actor);
			}
		}
		
		return intersecting;
	}
	
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		List<A> objects = ((World)(this.getParent())).getObjects(cls);
		for(A actor:objects) {
			if(!(actor == this)&&actor.getBoundsInParent().intersects(this.getBoundsInParent())) {
				return actor;
			}
		}
		
		return null;
	}
	
	public void playSound(){}
	
	public void addedToWorld() {}
	
	public abstract void act(long now);
	
}
