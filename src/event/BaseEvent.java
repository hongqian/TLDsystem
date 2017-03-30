package event;

import frame.base.Client.ClientInfoFrame;
import frame.base.carInfo.CarInfoFrame;

import frame.base.employee.EmpInfoFrame;
import frame.system.MainFrame;

import java.beans.PropertyVetoException;

/**
 * Created by 59480 on 2017/3/19.
 */
public class BaseEvent {

    private String event;
    private MainFrame mf;

    public BaseEvent(MainFrame mf, String event) {
        this.event = event;
        this.mf = mf;
    }

    public void doActionEvent() {
        if  (event.equals("车辆信息")) {
            try {
                CarInfoFrame carInfoFrame = new CarInfoFrame(mf);
                mf.desktopPane.add(carInfoFrame);
                carInfoFrame.setSelected(true);
            } catch (PropertyVetoException e1) {
                e1.printStackTrace();
            }
        } else if (event.equals("客户资料")) {
            try {
                ClientInfoFrame clientInfoFrame = new ClientInfoFrame(mf);
                mf.desktopPane.add(clientInfoFrame);
                clientInfoFrame.setSelected(true);
            } catch (PropertyVetoException e1) {
                e1.printStackTrace();
            }
        } else if (event.equals("员工资料")) {
            try {
                EmpInfoFrame employeeInfoFrame = new EmpInfoFrame(mf);
                mf.desktopPane.add(employeeInfoFrame);
                employeeInfoFrame.setSelected(true);
            } catch (PropertyVetoException e1) {
                e1.printStackTrace();
            }
        }
    }
}
