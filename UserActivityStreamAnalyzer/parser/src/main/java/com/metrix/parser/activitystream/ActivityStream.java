package com.metrix.parser.activitystream;

import com.ibm.common.activitystreams.Activity;

import static com.ibm.common.activitystreams.Makers.activity;
import static com.ibm.common.activitystreams.Makers.object;

/*
 *  This class will convert the java object into the activity stream 2.0 format and return back the format to the
 *  analyzer class
 * */
public class ActivityStream {
    String actorId;
    String actorType;
    String actorProvider;
    String verb;
    String objectType;
    String objectContent;
    String targetType;
    String targetContent;

    public ActivityStream(String actorId, String actorType, String actorProvider, String verb, String objectType,
                          String objectContent, String targetType, String targetContent) {
        this.actorId = actorId;
        this.actorType = actorType;
        this.actorProvider = actorProvider;
        this.verb = verb;
        this.objectType = objectType;
        this.objectContent = objectContent;
        this.targetType = targetType;
        this.targetContent = targetContent;
    }

    public ActivityStream() {
    }

    //Returning the data into AS2.0 format
    public Activity getActivity() {
        Activity activity = activity()
                .actor(object(actorType).id(actorId).provider(actorProvider))
                .object(object(objectType).content(objectContent))
                .verb(verb)
                .target(object(targetType).content(targetContent))
                .context("http://www.w3.org/ns/activitystreams")
                .get();
        return activity;
    }

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public String getActorType() {
        return actorType;
    }

    public void setActorType(String actorType) {
        this.actorType = actorType;
    }

    public String getActorProvider() {
        return actorProvider;
    }

    public void setActorProvider(String actorProvider) {
        this.actorProvider = actorProvider;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectContent() {
        return objectContent;
    }

    public void setObjectContent(String objectContent) {
        this.objectContent = objectContent;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetContent() {
        return targetContent;
    }

    public void setTargetContent(String targetContent) {
        this.targetContent = targetContent;
    }
}
