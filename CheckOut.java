package vcs;

import java.util.ArrayList;
import utils.ErrorCodeManager;
import utils.OperationType;
import filesystem.FileSystemEntity;

public class CheckOut extends VcsOperation {
    public CheckOut(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }
    public int execute(Vcs vcs) {
        if (vcs.getStage().getChanges().size() != 0) {
            return ErrorCodeManager.VCS_STAGED_OP_CODE;
        }
        if (operationArgs.size() == 2) {
            boolean is = false;
            for (int i = 0; i < vcs.getBranches().size(); ++i) {
                if (vcs.getBranches().get(i).getName().contentEquals(operationArgs.get(1))) {
                    is = true;
                    vcs.setHead(i);
                    break;
                }
            }
            if (!is) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        } else {
            boolean is = false;
            boolean bool;
            FileSystemEntity addEntity;
            String name = "";
            
            for (Commit com : vcs.getBranches().get(vcs.getHead()).getCommits()) {
                if (com.getId() == Integer.parseInt(operationArgs.get(2))) {
                    is = true;
                    break;
                }
            }
            if (!is) {
                return ErrorCodeManager.VCS_BAD_PATH_CODE;
            } else {
                //for (Commit com : vcs.getBranches().get(vcs.getHead()).getCommits()) {
                int size = vcs.getBranches().get(vcs.getHead()).getCommits().size();
                for (int j = 0; j < size; ++j) {
                    if (vcs.getBranches().get(vcs.getHead()).getCommits().get(j).getId()
                            == Integer.parseInt(operationArgs.get(2))) {
                        for (int i = j + 1; i < size; ++i) {
                            vcs.getBranches().get(vcs.getHead()).getCommits().remove(i);
                           
                        }
                        break;
                    }
                }
            }
            
            for (Commit com : vcs.getBranches().get(vcs.getHead()).getCommits()) {
                if (com.getId() == Integer.parseInt(operationArgs.get(2))) {
                    is = true;
                    for (int i = 0; i < vcs.getActiveSnapshot().getCurrentDir().getContent().size(); ++i) {
                        bool = false;
                        name = vcs.getActiveSnapshot().getCurrentDir().getContent().get(i).getName();
                        for (int j = 0; j < com.getEntities().size(); ++j) {
                            if (vcs.getActiveSnapshot().getCurrentDir().getContent().get(i).getId()
                                    == com.getEntities().get(j).getId()) {
                                bool = true;
                                break;
                            }
                        }
                        if (!bool) {
                            vcs.getActiveSnapshot().getCurrentDir().remove(name);
                            i--;
                        }
                    }
                    for (int i = 0; i < com.getEntities().size(); ++i) {
                        bool = false;
                        addEntity = com.getEntities().get(i);
                        for (int j = 0; j < vcs.getActiveSnapshot().getCurrentDir().getContent().size(); ++j) {
                            if (vcs.getActiveSnapshot().getCurrentDir().getContent().get(j).getId()
                                    == com.getEntities().get(i).getId()) {
                                bool = true;
                                break;
                            }
                        }
                        if (!bool) {
                            vcs.getActiveSnapshot().getCurrentDir().addEntity(addEntity);
                        }
                    }
                    break;
                }
            }
            
        }
        return ErrorCodeManager.OK;
    }
}
