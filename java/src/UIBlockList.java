import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;

public class UIBlockList extends UIAbstractList
{
    UIBlockList(){
        super();
    }

    protected void setList(){
        list = esql.ListBlocked();
    }

    protected String htmlFormatter(int index){
        return list.get(index).get(0);
    }

    protected void customizeButton(JButton button){}
}
