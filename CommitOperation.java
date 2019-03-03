package vcs;

import java.util.ArrayList;
import utils.ErrorCodeManager;
import utils.OperationType;

public class CommitOperation extends VcsOperation{
    public CommitOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
    public int execute(Vcs vcs) {
        if (vcs.getStage().getChanges().size() == 0) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }
        String s = "";
        for (int i = 2; i < operationArgs.size() - 1; ++i) {
            s += operationArgs.get(i) + " ";
        }
        s += operationArgs.get(operationArgs.size() - 1);
//        int size = vcs.getBranches().get(vcs.getHead()).getCommits().size();
//        if (size > 1) {
//            vcs.getBranches().get(vcs.getHead()).getCommits().remove(size - 1);
//        }
        Commit commit = new Commit(s, vcs.getActiveSnapshot());
        vcs.addCommit(commit);
        vcs.resetStage();
        return ErrorCodeManager.OK;
    }
    
}
