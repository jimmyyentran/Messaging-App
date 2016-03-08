import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

public class UIContactList extends UIAbstractList
{
    UIContactList(){
        super();
    }

    protected void setList(){
        list = esql.ListContacts();
    }

    protected String htmlFormatter(int index){
        return String.format("<html>%s<br />%s</html>", list.get(index).get(0).trim(), list.get(index).get(1).trim());
    }

    protected void customizeButton(JButton button){
    }
}
