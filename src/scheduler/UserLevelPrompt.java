package scheduler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import scheduler.TabbedGUI.Access;
/**
 * A prompt to identify the user as a regular user or  a principal
 * @author ben
 *
 */
public class UserLevelPrompt extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserLevelPrompt() {
		super("Identification");
		JPanel toplevel = new JPanel();
		toplevel.setLayout(new BoxLayout(toplevel, BoxLayout.Y_AXIS));
		JPanel jp = new JPanel();
		jp.add(new JLabel("name:"));
		JTextField name = new JTextField(20);
		jp.add(name);
		jp.add(new JLabel("email:"));
		JTextField email = new JTextField(20);
		jp.add(email);
		JPanel jp2 = new JPanel();
		JButton btnLogin = new JButton("login");
		JButton btnSkipToPrincipal = new JButton("I'm the principal");
		jp2.add(btnLogin);
		jp2.add(btnSkipToPrincipal);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TabbedGUI(name.getText(), email.getText());
				dispose();
			}
		});
		btnSkipToPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TabbedGUI(Access.PRINCIPAL);
				dispose();
			}
		});
		toplevel.add(jp);
		toplevel.add(jp2);
		add(toplevel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

}
