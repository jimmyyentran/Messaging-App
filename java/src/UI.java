import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UI extends JSplitPane implements ActionListener 
{
    private UIListener listener;
    private UITabbedPane tabbedPane;

    UI(){
        // setPreferredSize(new Dimension(650,350));
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setDividerLocation(150);

        tabbedPane = new UITabbedPane();

        setLeftComponent(tabbedPane);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void setRegistrationListener(UIListener listener) {
        this.listener = listener;
    }
}
