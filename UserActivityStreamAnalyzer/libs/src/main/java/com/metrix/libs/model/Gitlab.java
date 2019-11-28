package com.metrix.libs.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Gitlab {
    String object_kind;
    String event_name;
    String before;
    String after;
    String ref;
    String checkout_sha;
    String message;
    String user_id;
    String user_name;
    String user_username;
    String user_email;
    String user_avatar;
    String project_id;
    HashMap<String, Object> project;
    ArrayList<Object> commits;
    String total_commits_count;
    ArrayList<Object> push_options;
    HashMap<String, Object> repository;
    String event_type;
    HashMap<String, Object> user;
    HashMap<String, Object> object_attributes;
    ArrayList<Object> labels;
    HashMap<String, Object> changes;

    public Gitlab() {
    }

    public Gitlab(String object_kind, String event_name, String before, String after, String ref, String checkout_sha, String message, String user_id, String user_name, String user_username, String user_email, String user_avatar, String project_id, HashMap<String, Object> project, ArrayList<Object> commits, String total_commits_count, ArrayList<Object> push_options, HashMap<String, Object> repository, String event_type, HashMap<String, Object> user, HashMap<String, Object> object_attributes, ArrayList<Object> labels, HashMap<String, Object> changes) {
        this.object_kind = object_kind;
        this.event_name = event_name;
        this.before = before;
        this.after = after;
        this.ref = ref;
        this.checkout_sha = checkout_sha;
        this.message = message;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_username = user_username;
        this.user_email = user_email;
        this.user_avatar = user_avatar;
        this.project_id = project_id;
        this.project = project;
        this.commits = commits;
        this.total_commits_count = total_commits_count;
        this.push_options = push_options;
        this.repository = repository;
        this.event_type = event_type;
        this.user = user;
        this.object_attributes = object_attributes;
        this.labels = labels;
        this.changes = changes;
    }

    public String getObject_kind() {
        return object_kind;
    }

    public void setObject_kind(String object_kind) {
        this.object_kind = object_kind;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCheckout_sha() {
        return checkout_sha;
    }

    public void setCheckout_sha(String checkout_sha) {
        this.checkout_sha = checkout_sha;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public HashMap<String, Object> getProject() {
        return project;
    }

    public void setProject(HashMap<String, Object> project) {
        this.project = project;
    }

    public ArrayList<Object> getCommits() {
        return commits;
    }

    public void setCommits(ArrayList<Object> commits) {
        this.commits = commits;
    }

    public String getTotal_commits_count() {
        return total_commits_count;
    }

    public void setTotal_commits_count(String total_commits_count) {
        this.total_commits_count = total_commits_count;
    }

    public ArrayList<Object> getPush_options() {
        return push_options;
    }

    public void setPush_options(ArrayList<Object> push_options) {
        this.push_options = push_options;
    }

    public HashMap<String, Object> getRepository() {
        return repository;
    }

    public void setRepository(HashMap<String, Object> repository) {
        this.repository = repository;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public HashMap<String, Object> getUser() {
        return user;
    }

    public void setUser(HashMap<String, Object> user) {
        this.user = user;
    }

    public HashMap<String, Object> getObject_attributes() {
        return object_attributes;
    }

    public void setObject_attributes(HashMap<String, Object> object_attributes) {
        this.object_attributes = object_attributes;
    }

    public ArrayList<Object> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<Object> labels) {
        this.labels = labels;
    }

    public HashMap<String, Object> getChanges() {
        return changes;
    }

    public void setChanges(HashMap<String, Object> changes) {
        this.changes = changes;
    }

    @Override
    public String toString() {
        return "Gitlab{" +
                "object_kind='" + object_kind + '\'' +
                ", event_name='" + event_name + '\'' +
                ", before='" + before + '\'' +
                ", after='" + after + '\'' +
                ", ref='" + ref + '\'' +
                ", checkout_sha='" + checkout_sha + '\'' +
                ", message='" + message + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_username='" + user_username + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_avatar='" + user_avatar + '\'' +
                ", project_id='" + project_id + '\'' +
                ", project=" + project +
                ", commits=" + commits +
                ", total_commits_count='" + total_commits_count + '\'' +
                ", push_options=" + push_options +
                ", repository=" + repository +
                ", event_type='" + event_type + '\'' +
                ", user=" + user +
                ", object_attributes=" + object_attributes +
                ", labels=" + labels +
                ", changes=" + changes +
                '}';
    }
}
