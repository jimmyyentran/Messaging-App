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
        return "<html><b>" + list.get(index).get(0).trim() + "</b></html>";
    }

    protected void customizeButton(JButton button){}

    @Override
    protected void setAllActionListeners() {
        for(JButton a: users){
            a.addActionListener(this);
        }
    }

    @Override
    protected ImageIcon imageSelector(int index) {
        return null;
    }

    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        CardLayoutPanel.setBlockUsername(list.get(Integer.parseInt(cmd)).get(0));
    }
}
