package com.metrix.parser.activitystream;

import com.ibm.common.activitystreams.ASObject;
import com.ibm.common.activitystreams.Activity;
import com.ibm.common.activitystreams.LinkValue;
import com.ibm.common.activitystreams.TypeValue;
import com.metrix.libs.model.UserData;


/*
 * This class will convert the data of the Activity Stream 2.0 format to the java object
 * */
public class JavaObjects {
    Activity activity;
    UserData userObject;

    public UserData getUserData() {
        TypeValue tv = activity.verb();
        String verbs = tv.id();
        ASObject actor = (ASObject) activity.firstActor();
        String actorId = actor.id();
        String actorObjectType = actor.objectTypeString();
        ASObject targets = (ASObject) activity.firstTarget();
        String targetObjectType = targets.objectType().id();
        String targetContent = targets.content().toString();
        String objectContent = null, objectObjecttype = null;
        for (LinkValue object : activity.object()) {
            objectContent = ((ASObject) object).content().toString();
            objectObjecttype = ((ASObject) object).objectType().id();
        }
        userObject = new UserData(objectContent, objectObjecttype, verbs, targetObjectType, targetContent,
                actorObjectType, actorId);
        return userObject;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
