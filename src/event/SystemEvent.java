package event;

/**
 * Created by 59480 on 2017/3/19.
 */
import frame.system.AboutDialog;
import frame.system.LoginFrame;
import frame.system.MainFrame;
import frame.system.role.RoleManagerFrame;
import frame.system.user.UserManagerFrame;

import java.beans.PropertyVetoException;

import javax.swing.JOptionPane;


public class SystemEvent {

    private String event;
    private MainFrame mf;
    private String backName;

    public SystemEvent(MainFrame mf, String event) {
        this.event = event;
        this.mf = mf;
    }

    public void doActionEvent() {
         if (event.equals("角色管理")) {
            try {
                RoleManagerFrame roleFrame = new RoleManagerFrame(mf);
                mf.desktopPane.add(roleFrame);
                roleFrame.setSelected(true);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        } else if (event.equals("用户管理")) {
            try {
                UserManagerFrame userFrame = new UserManagerFrame(mf);
                mf.desktopPane.add(userFrame);
                userFrame.setSelected(true);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        } else if (event.equals("关于")) {
            try {
                AboutDialog aboutDialog=new AboutDialog(mf);
                mf.desktopPane.add(aboutDialog);
                aboutDialog.setSelected(true);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        } else if (event.equals("退出系统")) {
            int result = JOptionPane.showConfirmDialog(mf, "确认退出吗？", "温馨提示",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (event.equals("注销")) {
            int result = JOptionPane.showConfirmDialog(mf, "确认注销吗？", "温馨提示",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                mf.dispose();
                new LoginFrame();
            }
        }
    }
}
