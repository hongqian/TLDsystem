package event;

import frame.DistributionSites.DbSInfoFrame;
import frame.DistributionSites.DbSscope.ScopeInfoFrame;
import frame.system.MainFrame;
import frame.system.role.RoleManagerFrame;
import frame.system.user.UserManagerFrame;

import java.beans.PropertyVetoException;

/**
 * Created by 59480 on 2017/3/19.
 */
public class DbSEvent {
    private String event;
    private MainFrame mf;

    public DbSEvent(MainFrame mf, String event) {
        this.event = event;
        this.mf = mf;
    }

    public void doActionEvent() {
        if (event.equals("配送点信息")) {
            try {
                DbSInfoFrame dbsFrame = new DbSInfoFrame(mf);
                mf.desktopPane.add(dbsFrame);
                dbsFrame.setSelected(true);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        }else if (event.equals("配送范围信息")) {
            try {
                ScopeInfoFrame scopeFrame = new ScopeInfoFrame(mf);
                mf.desktopPane.add(scopeFrame);
                scopeFrame.setSelected(true);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        }

    }
}
