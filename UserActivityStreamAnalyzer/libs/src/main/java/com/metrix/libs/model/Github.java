package com.metrix.libs.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Github  {
    String ref;
    String before;
    String after;
    HashMap<String, Object> repository;
    HashMap<String, Object> pusher;
    HashMap<String, Object> sender;
    boolean created;
    boolean deleted;
    boolean forced;
    String base_ref;
    String compare;
    ArrayList<Object> commits;
    HashMap<String, Object> head_commit;
    String ref_type;
    String master_branch;
    String description;
    String pusher_type;
    String action;
    HashMap<String, Object> issue;
    HashMap<String, Object> changes;

    public Github() {
    }

    public Github(String ref, String before, String after, HashMap<String, Object> repository, HashMap<String
            , Object> pusher, HashMap<String, Object> sender, boolean created, boolean deleted, boolean forced
            , String base_ref, String compare, ArrayList<Object> commits, HashMap<String, Object> head_commit,
                             String ref_type, String master_branch, String description, String pusher_type,
                             String action, HashMap<String, Object> issue, HashMap<String, Object> changes) {
        this.ref = ref;
        this.before = before;
        this.after = after;
        this.repository = repository;
        this.pusher = pusher;
        this.sender = sender;
        this.created = created;
        this.deleted = deleted;
        this.forced = forced;
        this.base_ref = base_ref;
        this.compare = compare;
        this.commits = commits;
        this.head_commit = head_commit;
        this.ref_type = ref_type;
        this.master_branch = master_branch;
        this.description = description;
        this.pusher_type = pusher_type;
        this.action = action;
        this.issue = issue;
        this.changes = changes;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public HashMap<String, Object> getRepository() {
        return repository;
    }

    public void setRepository(HashMap<String, Object> repository) {
        this.repository = repository;
    }

    public HashMap<String, Object> getPusher() {
        return pusher;
    }

    public void setPusher(HashMap<String, Object> pusher) {
        this.pusher = pusher;
    }

    public HashMap<String, Object> getSender() {
        return sender;
    }

    public void setSender(HashMap<String, Object> sender) {
        this.sender = sender;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isForced() {
        return forced;
    }

    public void setForced(boolean forced) {
        this.forced = forced;
    }

    public String getBase_ref() {
        return base_ref;
    }

    public void setBase_ref(String base_ref) {
        this.base_ref = base_ref;
    }

    public String getCompare() {
        return compare;
    }

    public void setCompare(String compare) {
        this.compare = compare;
    }

    public ArrayList<Object> getCommits() {
        return commits;
    }

    public void setCommits(ArrayList<Object> commits) {
        this.commits = commits;
    }

    public HashMap<String, Object> getHead_commit() {
        return head_commit;
    }

    public void setHead_commit(HashMap<String, Object> head_commit) {
        this.head_commit = head_commit;
    }

    public void setHead_commits(HashMap<String, Object> head_commit) {
        this.head_commit = head_commit;
    }

    public String getRef_type() {
        return ref_type;
    }

    public void setRef_type(String ref_type) {
        this.ref_type = ref_type;
    }

    public String getMaster_branch() {
        return master_branch;
    }

    public void setMaster_branch(String master_branch) {
        this.master_branch = master_branch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPusher_type() {
        return pusher_type;
    }

    public void setPusher_type(String pusher_type) {
        this.pusher_type = pusher_type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public HashMap<String, Object> getIssue() {
        return issue;
    }

    public void setIssue(HashMap<String, Object> issue) {
        this.issue = issue;
    }

    public HashMap<String, Object> getChanges() {
        return changes;
    }

    public void setChanges(HashMap<String, Object> changes) {
        this.changes = changes;
    }

    @Override
    public String toString() {
        return "Github{" +
                "ref='" + ref + '\'' +
                ", before='" + before + '\'' +
                ", after='" + after + '\'' +
                ", repository=" + repository +
                ", pusher=" + pusher +
                ", sender=" + sender +
                ", created=" + created +
                ", deleted=" + deleted +
                ", forced=" + forced +
                ", base_ref='" + base_ref + '\'' +
                ", compare='" + compare + '\'' +
                ", commits=" + commits +
                ", head_commit=" + head_commit +
                ", ref_type='" + ref_type + '\'' +
                ", master_branch='" + master_branch + '\'' +
                ", description='" + description + '\'' +
                ", pusher_type='" + pusher_type + '\'' +
                ", action='" + action + '\'' +
                ", issue=" + issue +
                ", changes=" + changes +
                '}';
    }
}