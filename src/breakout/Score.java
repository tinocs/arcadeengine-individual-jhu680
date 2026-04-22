/* Name: Jethro Hu
 * Date: Apr 20, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;


import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score extends Text{
	private int scoreVal;

	public Score() {
		scoreVal = 0;
		setFont(new Font(getFont().getSize()*3/2));
		updateDisplay();
	}
	
	public void updateDisplay() {
		setText(scoreVal+"");
	}
	
	public int getScoreVal() {
		return scoreVal;
	}

	public void setScoreVal(int scoreVal) {
		this.scoreVal = scoreVal;
		updateDisplay();
	}
}
