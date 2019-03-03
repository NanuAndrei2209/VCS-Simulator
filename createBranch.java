package vcs;

import java.util.ArrayList;
import utils.ErrorCodeManager;
import utils.OperationType;

public class createBranch extends VcsOperation {
    public createBranch(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
    public int execute(Vcs vcs) {
        Branch newBranch = new Branch(operationArgs.get(1));
        for (Branch i : vcs.getBranches()) {
            if (i.getName().contentEquals(newBranch.getName())) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }
        vcs.addBranch(newBranch);
        return ErrorCodeManager.OK;
    }
    
}
