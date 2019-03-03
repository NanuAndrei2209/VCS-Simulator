package vcs;

import java.util.ArrayList;
import utils.ErrorCodeManager;
import utils.OperationType;

public class Invalid_Vcs_Operation extends VcsOperation {
    public Invalid_Vcs_Operation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
    public int execute(Vcs vcs) {
        return ErrorCodeManager.VCS_BAD_CMD_CODE;
    }
}
