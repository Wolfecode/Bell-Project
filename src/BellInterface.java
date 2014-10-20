import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BellInterface extends JPanel {
	private JTextField input;
	private JButton on;
	private JButton off;

	public BellInterface() {

		on = new JButton("On");
		off = new JButton("Off");

		this.add(on);
		this.add(off);
	}
}