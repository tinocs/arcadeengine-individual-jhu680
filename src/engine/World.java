/* Name: Jethro Hu
 * Date: Apr 7, 2026
 * Period: 1
 * Does it work: yes
 */

package engine;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane{
	private AnimationTimer timer;
	private boolean timerRunning;
	private ArrayList<KeyCode> keysPressed;
	private boolean widthSet;
	private boolean heightSet;
	
	
	public World() {
		timer = new AnimationTimer() {
			long past = -1;
			@Override
			public void handle(long now) {
				//if(now-past>=1e9){
					act(now);
					int size = getChildren().size();
					for(int i = 0;i<getChildren().size();i++) {
						if(size!=getChildren().size()) {
							i--;
							size = getChildren().size();
						}
						Actor actor = (Actor)(getChildren().get(i));
						if(actor!=null) {
							actor.act(now);
						}
					}
					//System.out.println("heer\n");
				//}	
			}
		};
		timerRunning = false;
		keysPressed = new ArrayList<>();
		widthSet = false;
		heightSet = false;
		this.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(!widthSet&&newValue.doubleValue()>0.0) {
					widthSet = true;
					if(heightSet) {
						onDimensionsInitialized();
					}
				}
				
			}
			
		});
		
		this.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if(!heightSet&&newValue.doubleValue()>0.0) {
					heightSet = true;
					if(widthSet) {
						onDimensionsInitialized();
					}
				}
				
			}
			
		});
		
		this.sceneProperty().addListener(new ChangeListener<Scene>(){

			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if(newValue !=null) {
					requestFocus();
				}
			}
			
		});
		
		this.setOnKeyPressed(e->keysPressed.add(e.getCode()));
		this.setOnKeyReleased(e -> keysPressed.remove(e.getCode()));
	
	}
	
	public void start() {
		timer.start();
		timerRunning = true;
	}
	
	public void stop() {
		timer.stop();
		timerRunning = false;
	}
	
	public boolean isStopped() {
		return !timerRunning;
	}
	
	public void add(Actor actor) {
		//actor.addedToWorld();
		this.getChildren().add(actor);
		actor.addedToWorld();
	}
	
	public void remove(Actor actor) {
		ObservableList<Node> children = this.getChildren();
		for(int i = 0;i<children.size();i++) {
			if(children.get(i).equals(actor)) {
				children.remove(i);
			}
		}
	}
	
	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls){
		List<A> actors = new ArrayList<A>();
		ObservableList<Node> children = this.getChildren();
		for(int i = 0;i<children.size();i++) {
			if(cls.isInstance(children.get(i))){
				actors.add(cls.cast(children.get(i)));
			}
		}
		return actors;
	}
	
	public <A extends Actor> java.util.List<A> getObjectsAt(double x, double y, java.lang.Class<A> cls){
		
		List<A> actors = new ArrayList<>();
		ObservableList<Node> children = this.getChildren();
		for(int i = 0;i<children.size();i++) {
			Actor child = (Actor)(children.get(i));
			Bounds bound = child.getBoundsInParent();
			if(bound.contains(x, y)) {
				try {
					actors.add(cls.cast(child));
				}catch(Exception e) {}
			}
		}
		return actors;
	}
	
	public boolean isKeyPressed(javafx.scene.input.KeyCode code) {
		for(int i = 0;i<keysPressed.size();i++) {
			if(keysPressed.get(i).equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	public abstract void onDimensionsInitialized();
	
	public abstract void act(long now);
	
}
