package vcs;
import utils.IDGenerator;
import filesystem.FileSystemEntity;
import filesystem.FileSystemSnapshot;

import java.util.ArrayList;
public class Commit {
    private int id;
    private String message;
    private ArrayList<FileSystemEntity> entities;
    public Commit(String mesaj, FileSystemSnapshot snapshot) {
        this.id = IDGenerator.generateCommitID();
        this.message = mesaj;
        entities = new ArrayList<FileSystemEntity>();
        for (FileSystemEntity entity : snapshot.getCurrentDir().getContent()) {
            entities.add(entity);
        }
    }
    
    public int getId() {return this.id;}
    public String getMessage() {return this.message;}
    public ArrayList<FileSystemEntity> getEntities() {return this.entities;}
}
