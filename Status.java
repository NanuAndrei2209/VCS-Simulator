package vcs;

import java.util.ArrayList;
import utils.ErrorCodeManager;
import utils.OperationType;

public class Status extends VcsOperation {
    public Status(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
    public int execute(Vcs vcs) {
        vcs.getOutputWriter().write("On branch: " + vcs.getBranches().get(vcs.getHead()).getName() + '\n');
        vcs.getOutputWriter().write("Staged changes:" + '\n');
        for (int i = 0; i < vcs.getStage().getChanges().size(); ++i) {
            vcs.getOutputWriter().write(vcs.getStage().getChanges().get(i) + '\n');
        }
        return ErrorCodeManager.OK;
    }
}
