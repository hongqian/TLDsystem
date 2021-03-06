package frame.system.user;


import dao.System.RoleDao;
import dao.System.UserDao;
import entity.Role;
import entity.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUserDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAccount;
	private JTextArea txtComment;
	private JComboBox listStatus;
	private JComboBox listRole;
	private JButton btnEdit;
	private JButton btnCancel;
	private JPasswordField pwdFirst;
	private JPasswordField pwdSecond;
	UserManagerFrame userManaerFrame;
	int userId;

	/**
	 * Create the dialog.
	 */
	public EditUserDialog(UserManagerFrame userManaerFrame, int userId) {
		this.userManaerFrame = userManaerFrame;
		this.userId = userId;
		setTitle("修改用户");
		setBounds(100, 100, 349, 337);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u7528\u6237\u4FE1\u606F",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel.setBounds(0, 0, 333, 258);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("帐    号:");
		label.setBounds(44, 13, 54, 15);
		panel.add(label);

		txtAccount = new JTextField();
		txtAccount.setColumns(10);
		txtAccount.setBounds(101, 15, 193, 21);
		txtAccount.setEditable(false);
		panel.add(txtAccount);

		JLabel label_1 = new JLabel("密    码:");
		label_1.setBounds(44, 52, 54, 15);
		panel.add(label_1);

		JLabel label_2 = new JLabel("用户状态");
		label_2.setBounds(44, 162, 54, 15);
		panel.add(label_2);

		listStatus = new JComboBox();
		listStatus.setModel(new DefaultComboBoxModel(
				new String[] { "启用", "注销" }));
		listStatus.setBounds(101, 159, 193, 21);
		panel.add(listStatus);

		listRole = new JComboBox();
		RoleDao rd=new RoleDao();
		listRole.setModel(new DefaultComboBoxModel(
				rd.loadName()));
		listRole.setBounds(101, 123, 193, 21);
		panel.add(listRole);

		JLabel label_3 = new JLabel("角色权限");
		label_3.setBounds(42, 123, 54, 15);
		panel.add(label_3);

		JLabel label_4 = new JLabel("备    注:");
		label_4.setBounds(44, 193, 54, 15);
		panel.add(label_4);

		txtComment = new JTextArea();
		txtComment.setBounds(102, 195, 193, 43);
		panel.add(txtComment);

		JLabel label_5 = new JLabel("确认密码:");
		label_5.setBounds(44, 89, 54, 15);
		panel.add(label_5);

		pwdFirst = new JPasswordField();
		pwdFirst.setEchoChar('*');
		pwdFirst.setBounds(101, 49, 193, 21);
		panel.add(pwdFirst);

		pwdSecond = new JPasswordField();
		pwdSecond.setEchoChar('*');
		pwdSecond.setBounds(101, 86, 193, 21);
		panel.add(pwdSecond);

		btnEdit = new JButton("修改");
		btnEdit.addActionListener(this);
		btnEdit.setBounds(69, 268, 73, 23);
		contentPanel.add(btnEdit);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(187, 268, 73, 23);
		contentPanel.add(btnCancel);

		// 加载要修改id的信息
		UserDao userDao = new UserDao();
		User user = userDao.findUser(userId);
		txtAccount.setText(user.getUserAccount());
		pwdFirst.setText(user.getUserPwd());
		pwdSecond.setText(user.getUserPwd());
		listRole.setSelectedItem(user.getRoleId().getRoleName());
		listStatus.setSelectedItem(user.getUserStatus() == 1 ? "启用" : "注销");
		txtComment.setText(user.getUserComm());

		this.setLocationRelativeTo(userManaerFrame);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEdit) {
			// 收集信息
			String userAccount = txtAccount.getText();
			String firstPwd = new String(pwdFirst.getPassword());
			String secondPwd = new String(pwdSecond.getPassword());
			int userStatus = listStatus.getSelectedItem().equals("启用") ? 1 : 0;
			String userComm = txtComment.getText();
			Role role = new Role();
			role.setRoleName((String) listRole.getSelectedItem());
			if (firstPwd.equals(secondPwd)) {
				int result = JOptionPane
						.showConfirmDialog(this, "确认修改 " + userAccount
								+ " 的信息吗?", "温馨提示", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					User user = new User(userId, userAccount, firstPwd,
							userStatus, userComm, role);
					// 调用DAO
					UserDao userDao = new UserDao();
					userDao.updateUser(user);
					// 刷新
					userManaerFrame.loadUser();
					this.dispose();
				}
			} else {
				JOptionPane.showMessageDialog(this, "两次密码不一样,请重新输入!");
			}
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
