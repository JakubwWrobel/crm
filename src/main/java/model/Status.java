package model;

import java.sql.Statement;
import java.util.HashSet;

public enum Status {
    RECEIVED, COST_APPROVED, INPROGRESS, READY_TO_PICK, RESIGNED;

    public static HashSet<String> getStatuses() {
        HashSet<String> statuses = new HashSet<String>();

        for (Status s : Status.values()) {
            statuses.add(s.name());
        }
        return statuses;

    }

/*    public static String getStatus(String s){
        return Status;
    }*/
}
