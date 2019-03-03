package vcs;
import vcs.Commit;
import java.util.ArrayList;
public class Branch {
    private String name;
    private ArrayList<Commit> commits;
    
    public Branch(String nume, ArrayList<Commit> commituri) {
        this.name = nume;
        this.commits = new ArrayList<Commit>();
        for (Commit c : commituri) {
            this.commits.add(c);
        }
    }
    public Branch(String nume) {
        this.name = nume;
        this.commits = new ArrayList<Commit>();
    }
    
    public String getName() {return this.name;}
    public ArrayList<Commit> getCommits() {return this.commits;}
    public void addCommit(Commit commit) {
        commits.add(commit);
    }
}
