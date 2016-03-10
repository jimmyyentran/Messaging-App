import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

public class UIBlockList extends UIAbstractList implements ActionListener
{
    UIBlockList(){
        super();
    }

    protected void setList(){
        list = esql.ListBlocked();
    }

    protected String htmlFormatter(int index){
        return list.get(index).get(0).trim();
    }

    protected void customizeButton(JButton button){}

    @Override
    protected void setAllActionListeners() {
        for(JButton a: users){
            a.addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        CardLayoutPanel.setBlockUsername(list.get(Integer.parseInt(cmd)).get(0));
    }
}
