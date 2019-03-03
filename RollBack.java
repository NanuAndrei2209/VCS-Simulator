package vcs;

import java.util.ArrayList;

import filesystem.FileSystemEntity;
import utils.ErrorCodeManager;
import utils.OperationType;

public class RollBack extends VcsOperation {
    public RollBack(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
    public int execute(Vcs vcs) {
        vcs.resetStage();
        boolean bool;
        FileSystemEntity addEntity;
        String name = "";
        for (int i = 0; i < vcs.getActiveSnapshot().getCurrentDir().getContent().size(); ++i) {
            bool = false;
            name = vcs.getActiveSnapshot().getCurrentDir().getContent().get(i).getName();
            int size = vcs.getBranches().get(vcs.getHead()).getCommits().size();
                for (int j = 0; j < vcs.getBranches().get(vcs.getHead()).getCommits().get(size - 1).getEntities().size(); ++j) {
                    if (vcs.getActiveSnapshot().getCurrentDir().getContent().get(i).getId()
                            == vcs.getBranches().get(vcs.getHead()).getCommits().get(size - 1).getEntities().get(j).getId()) {
                        bool = true;
                        break;
                    }
                }
                if (!bool) {
                    vcs.getActiveSnapshot().getCurrentDir().remove(name);
                    i--;
                }
         }
        return ErrorCodeManager.OK;
    }
}
