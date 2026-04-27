/* Name: Jethro Hu
 * Date: Apr 26, 2026
 * Period: 1
 * Does it work: yes
 */

package breakout;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Lives extends Text{
	
	private int livesVal;

	public Lives() {
		livesVal = 0;
		setFont(new Font(getFont().getSize()*3/2));
		updateDisplay();
	}
	
	public void updateDisplay() {
		setText("Lives: "+livesVal);
	}
	
	public int getLivesVal() {
		return livesVal;
	}

	public void setLivesVal(int livesVal) {
		this.livesVal = livesVal;
		updateDisplay();
	}
}
