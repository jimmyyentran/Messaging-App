/**
 * Created by jimmytran on 3/9/16.
 */
public interface CardLayoutPanelListener {
    public void removeContactEventOccurred(String s);
    public void removeBlockEventOccurred(String s);
    public void newMessageEventOccurred(String s);
    public void addUserToChatEventOccurred(String s);
    public void removeUserFromChatEventOccurred(String s);
}
