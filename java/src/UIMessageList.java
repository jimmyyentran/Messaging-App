import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

public class UIMessageList extends UIAbstractList
{
    UIMessageList(){
        super();
    }

    protected void setList(){
        list = esql.ListMessages();
    }

    protected String htmlFormatter(int index){
        return null;
    }

    public void loadButtons(){
        removeAll();
        setList();

        for(List<String> tuple : list){
            for(String attribute : tuple){
                System.out.println(attribute);
            }
        }

        users = new JButton[list.size()];

        for(int i = 0; i < list.size(); i++){
            users[i] = makeUser(i);
            if(list.get(i).get(1).equals("group")){
                //print group
            } else {
                users[i].setText(list.get(i).get(2));
            }
        }

        for(JButton a : users){
            add(a);
        }
    }

    protected void customizeButton(JButton button){
    }
}
