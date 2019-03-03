package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public class Log extends VcsOperation {
    public Log(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
    public int execute(Vcs vcs) {
        int n = vcs.getBranches().get(vcs.getHead()).getCommits().size();
        for (int i = 0; i < n - 1; ++i) {
            vcs.getOutputWriter().write("Commit id: " + vcs.getBranches().get(vcs.getHead()).getCommits().get(i).getId() + '\n');
            vcs.getOutputWriter().write("Message: " + vcs.getBranches().get(vcs.getHead()).getCommits().get(i).getMessage() + '\n' + '\n');
        }
        vcs.getOutputWriter().write("Commit id: " + vcs.getBranches().get(vcs.getHead()).getCommits().get(n - 1).getId() + '\n');
        vcs.getOutputWriter().write("Message: " + vcs.getBranches().get(vcs.getHead()).getCommits().get(n - 1).getMessage() + '\n');
        return ErrorCodeManager.OK;
    }

}
