package vcs;
import java.util.ArrayList;
public class Stage {
    private ArrayList<String> changes;
    public Stage() {
        changes = new ArrayList<String>();
    }
    public ArrayList<String> getChanges() {return this.changes;}
    public void addChange(String s) {
        changes.add(s);
    }
    public void clearStage() {
        this.changes.clear();
    }
}
