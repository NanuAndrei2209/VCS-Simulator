package vcs;
import java.util.ArrayList;
import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.OutputWriter;
import utils.Visitor;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    private ArrayList<Branch> branches;
    private int head;
    private Stage stage;
    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);

        //TODO other initialisations
        ArrayList<Commit> commits = new ArrayList<Commit>();
        commits.add(new Commit("First commit", activeSnapshot));
        this.branches = new ArrayList<Branch>();
        branches.add(new Branch("master", commits));
        this.stage = new Stage();
        this.head = 0;
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        //TODO
        return vcsOperation.execute(this);
    }

    //TODO methods through which vcs operations interact with this
    public ArrayList<Branch> getBranches() {return this.branches;}
    public void addBranch(Branch newBranch) {
        this.branches.add(newBranch);
    }
    public Stage getStage() {return this.stage;}
    public void addToStage(String s) {
        stage.addChange(s);
    }
    public void addCommit(Commit commit) {
        this.branches.get(this.head).addCommit(commit);
    }
    public int getHead() {return this.head;}
    public void setHead(int x) {this.head = x;}
    public OutputWriter getOutputWriter() {return this.outputWriter;}
    public FileSystemSnapshot getActiveSnapshot() {return this.activeSnapshot;}
    public void resetStage() {this.stage.clearStage();}
}
