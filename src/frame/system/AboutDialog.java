package frame.system;

import javax.swing.*;

public class AboutDialog extends JInternalFrame {

	private MainFrame mf;

	/**
	 * Create the dialog.
	 */
	public AboutDialog(MainFrame mf) {
		super("关于本程序",true,true,false,true);
		this.mf = mf;
		setBounds(100, 100, 620, 480);

		JLabel lblImage = new JLabel("");
		lblImage.setBounds(0, 0, 614, 452);


		getContentPane().setLayout(null);
		getContentPane().add(lblImage);

		int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
		int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}
}
