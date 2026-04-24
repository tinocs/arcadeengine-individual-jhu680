/* Name: Jethro Hu
 * Date: Apr 22, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import engine.World;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class Level extends World{
	private int level;
	private boolean initialized;
	private int[][] grid;
	private BallWorld world;
	
	
	public Level(BallWorld world) {
		setPrefSize(640, 400);
		level = -1;
		this.world = world;
	}
	
	public Level(BallWorld world, int level) {
		this(world);
		this.level = level;
		
	}

	@Override
	public void onDimensionsInitialized() {
		//level builder
		if(level == -1) {
			grid = new int[20][20];
			Line line = new Line(0,getHeight()*4/5,getWidth(),getHeight()*4/5);
			world.getChildren().add(line);
			setOnMouseClicked(new EventHandler<MouseEvent>(){
				
				@Override
				public void handle(MouseEvent e) {
					
					int[] point = mouseToGrid(new double[] {e.getX(), e.getY()});
					try {
						grid[point[0]][point[1]]++;
						updateScreen();
					}catch(IndexOutOfBoundsException k) {
						Alert alert = new Alert(AlertType.ERROR, "Please place the bricks within range");
						alert.show();
					}
					
				}
				
			});
			
			setOnKeyPressed(new EventHandler<KeyEvent>(){

				@Override
				public void handle(KeyEvent e) {
					if(e.getCode() == KeyCode.S) {
						Scanner in = new Scanner(System.in);
						System.out.print("Enter file name: ");
						try {
							FileWriter pen = new FileWriter(in.next()+".txt");
							pen.append(grid.length+" "+grid[0].length+"\n");
							for(int r = 0;r<grid.length;r++) {
								for(int c = 0;c<grid[0].length;c++) {
									pen.append(grid[r][c]+" ");
								}
								pen.append("\n");
							}
							pen.close();
						} catch (Exception c){}
					}else if(e.getCode() == KeyCode.L) {
						try {
							JFileChooser file = new JFileChooser();
							file.showOpenDialog(null);
							load(file.getSelectedFile(), world);
							//FileReader reader = new FileReader(new JFileChooser().getSelectedFile());
							
						} catch (Exception c) {}
					}else if(e.getCode() == KeyCode.C) {
						grid = new int[grid.length][grid[0].length];
						updateScreen();
					}
				}
				
			});
		}
		
	}
	
	private void updateScreen() {
		for(int r = 0;r<grid.length;r++) {
			for(int c = 0;c<grid[0].length;c++) {
				double[] point = gridToMouse(new int[] {r,c});
				
				if(grid[r][c] > 2||grid[r][c] == 0) {
					ArrayList<Brick> list = (ArrayList<Brick>) getObjectsAt(point[0]+16, point[1]+8, Brick.class);
					if(list.size()!=0) {
						remove(list.get(0));
					}
					grid[r][c] = 0;
				}else if(grid[r][c]!=0) {
					Brick brick;
					
					if(grid[r][c] == 2) {
						ArrayList<Brick> list = (ArrayList<Brick>) getObjectsAt(point[0]+16, point[1]+8, Brick.class);
						if(list.size()==0) {
							brick = new Brick(2);
							brick.move(point[0], point[1]);
							world.add(brick);
						}else {
							brick = list.get(0);
							brick.setStrength(2);
						}
					}else if(getObjectsAt(point[0]+16,point[1]+8, Brick.class).size()==0){
						brick = new Brick(1);
						brick.move(point[0], point[1]);
						world.add(brick);
					}
					
					
				}
			}
		}
	}
	
	public void load(File f, BallWorld world) throws FileNotFoundException, NullPointerException{
		Scanner in = new Scanner(f);
		int[][] temp = new int[in.nextInt()][in.nextInt()];
		int r = 0;
		int c = 0;
	
		while(in.hasNextInt()) {
			temp[r][c] = in.nextInt();
			c++;
			if(c%temp[0].length==0) {
				r++;
				c=0;
			}
		
		}

		in.close();
		
		grid = new int[temp.length][temp[0].length];
	
		for(r = 0;r<temp.length;r++) {
			for(c = 0;c<temp[0].length;c++) {
				grid[r][c] = temp[r][c];
			}
		}
		updateScreen();
	}
	
	public void load(BallWorld world) throws FileNotFoundException, NullPointerException{
		JFileChooser file = new JFileChooser();
		file.showOpenDialog(null);
		Scanner in = new Scanner(file.getSelectedFile());
		int[][] temp = new int[in.nextInt()][in.nextInt()];
		int r = 0;
		int c = 0;
	
		while(in.hasNextInt()) {
			temp[r][c] = in.nextInt();
			c++;
			if(c%temp[0].length==0) {
				r++;
				c=0;
			}
		
		}

		in.close();
		
		grid = new int[temp.length][temp[0].length];
	
		for(r = 0;r<temp.length;r++) {
			for(c = 0;c<temp[0].length;c++) {
				grid[r][c] = temp[r][c];
			}
		}
		updateScreen();
	}
	private int[] mouseToGrid(double[] point) {
		return new int[] {(int)(point[0]/32), (int)(point[1]/16)};
	}
	
	private double[] gridToMouse(int[] point) {
		return new double[] {point[0]*32, point[1]*16};
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
}
