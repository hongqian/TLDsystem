package event;

import frame.DeliveryRecript.DRInfoFrame;
import frame.DeliveryRecript.ShowDRFrame;
import frame.system.MainFrame;

import java.beans.PropertyVetoException;

/**
 * Created by 59480 on 2017/3/21.
 */
public class DistributionEvent {

    private String event;
    private MainFrame mf;

    public DistributionEvent(MainFrame mf, String event) {
        this.event = event;
        this.mf = mf;
    }

    public void doActionEvent() {
        if (event.equals("交接单管理")) {
            try {
                DRInfoFrame drInfoFrame=new DRInfoFrame(mf);
                mf.desktopPane.add(drInfoFrame);
                drInfoFrame.setSelected(true);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        }else if (event.equals("查看交接单")) {
            try {
                ShowDRFrame showDRFrame=new ShowDRFrame(mf);
                mf.desktopPane.add(showDRFrame);
                showDRFrame.setSelected(true);
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        }

    }
}
