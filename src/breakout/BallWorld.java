/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;
import java.io.File;
import java.io.FileNotFoundException;

import engine.Sound;
import engine.World;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class BallWorld extends World{
	private Paddle paddle;
	private Score score;
	private int level;
	private Level levelWorld;
	private Stage stage;
	private Breakout breakout;
	private Ball ball;
	private Lives lives;
	private boolean win;
	private boolean isPaused;
	private int livesNum;
	private boolean attachedToPaddle;
	private Sound soundLife;
	private Sound soundLost;
	private Sound soundWon;
	
	public BallWorld(Stage stage, Breakout b) {
		setPrefSize(640, 400);
		level = 1;
		levelWorld = new Level(this, level);
		this.stage = stage;
		breakout = b;
		attachedToPaddle = true;
		soundLife = new Sound("breakoutresources/lose_life.wav");
		soundLost = new Sound("breakoutresources/game_lost.wav");
		soundWon = new Sound("breakoutresources/game_won.wav");
	}
	
	public BallWorld(int level, Stage stage, Breakout b) {
		this(stage, b);
		this.level = level;
		this.stage = stage;
		//levelWorld = new Level(this, level);
	}
	
	
	@Override
	public void onDimensionsInitialized() {
		win = false;
		paddle = new Paddle();
		paddle.move(this.getWidth()/2, this.getHeight()*4/5);
		add(paddle);
		
		ball = new Ball();
		ball.move(paddle.getX()+paddle.getWidth()/2-ball.getWidth()/2-ball.getX(), paddle.getY()-ball.getHeight()-ball.getY());
		add(ball);
		
		
		
		/*for(int i = 0;i<5;i++) {
			Brick brick = new Brick();
			brick.move(this.getWidth()/2-brick.getWidth()*5+brick.getWidth()*i, this.getHeight()*2/3);
			add(brick);
		}*/
		
		score = new Score();
		score.setX(this.getWidth()/2-score.getBoundsInParent().getWidth()/2-100);
		score.setY(score.getBoundsInParent().getHeight());
		getChildren().add(score);
		
		lives = new Lives();
		lives.setX(this.getWidth()/2-lives.getBoundsInParent().getWidth()/2+100);
		lives.setY(lives.getBoundsInParent().getHeight());
		lives.setLivesVal(3);
		getChildren().add(lives);
		
		
		setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				paddle.move(event.getX()-paddle.getX()-paddle.getWidth()/2,0);
			}
		
		});
		livesNum = lives.getLivesVal(); 
		start();
		isPaused = true;
		setOnMouseClicked(e ->{
			if(e.getButton()==MouseButton.PRIMARY) {
				isPaused = !isPaused;
				attachedToPaddle = false;
			}
		});
	}

	public Score getScore() {
		return score;
	}
	
	public Lives getLives() {
		return lives;
	}
	
	@Override
	public void act(long now) {
		if(isPaused) {
		}else {
			if(getLives().getLivesVal()<=0) {
				endGame();
			}
			
			if(livesNum != lives.getLivesVal()) {
				if(livesNum!=0) {
					soundLife.play();
				}
				if(ball.getDisplacement()[1]>0) {
					ball.setDy(ball.getDisplacement()[1]*-1);
				}
				livesNum = lives.getLivesVal();
				ball.move(paddle.getX()+paddle.getWidth()/2-ball.getWidth()/2-ball.getX(), paddle.getY()-ball.getHeight()-ball.getY());
				isPaused = true;
				attachedToPaddle = true;
				return;
			}
		// TODO Auto-generated methodstub
			if(isKeyPressed(KeyCode.LEFT)) {
				paddle.move(-3,0);
			}else if(isKeyPressed(KeyCode.RIGHT)) {
				paddle.move(3, 0);
			}
			
			
			
			if(getObjects(Brick.class).size()==0) {
				level++;
				isPaused = true;
				attachedToPaddle = true;
				try {
					ball.randomize();
					ball.move(paddle.getX()+paddle.getWidth()/2-ball.getWidth()/2-ball.getX(), paddle.getY()-ball.getHeight()-ball.getY());
					levelWorld.load(new File("level"+level+".txt"), this );
				
				} catch (Exception e) {
					win = true;
					endGame();
				}
				
			}
			
		}
		
		if(isKeyPressed(KeyCode.SPACE)) {
			
			isPaused = !isPaused;
			attachedToPaddle = false;
		}
		
		if(attachedToPaddle) {
			ball.move(paddle.getX()+paddle.getWidth()/2-ball.getWidth()/2-ball.getX(), paddle.getY()-ball.getHeight()-ball.getY());
		}
		
	}
	
	public void clear() {
		levelWorld.clear();
	}
	
	public boolean isGamePaused() {
		return isPaused;
	}
	
	public void setGamePaused(boolean b) {
		isPaused = b;
	}
	
	private void endGame() {
		isPaused = true;
		Alert end;
		if(win) {
			end = new Alert(AlertType.INFORMATION, "You Win!!");
			soundWon.play();
		}else {
			end = new Alert(AlertType.INFORMATION, "You Lose :(");
			soundLost.play();
		}
		end.show();
		end.setOnCloseRequest(e ->{
			breakout.setTitle();
			score.setScoreVal(0);
			level = 0;
			ball.move(paddle.getX()+paddle.getWidth()/2-ball.getWidth()/2-ball.getX(), paddle.getY()-ball.getHeight()-ball.getY());
			win = false;
		});
	}
}
