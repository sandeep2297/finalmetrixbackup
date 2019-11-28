package com.metrix.parser.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.common.activitystreams.Activity;
import com.metrix.libs.model.Gitlab;
import com.metrix.libs.model.UserData;
import com.metrix.parser.activitystream.ActivityStream;
import com.metrix.parser.activitystream.JavaObjects;
import com.metrix.parser.exception.DataNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/* Data from the gitlab is coming in this format*/
@Component
public class GitLabTransformer implements ActivityStreamTransformer {

    public Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public UserData convertToActivityStream(HashMap<String, Object> userdata, HashMap<String, String> headers) {

        ObjectMapper mapper = new ObjectMapper();
        Gitlab gitLabData = mapper.convertValue(userdata, Gitlab.class);

        UserData userObject = null;
        String actorId;
        String actorType;
        String actorProvider;
        String verb;
        String objectType = null;
        String objectContent = null;
        String targetType;
        String targetContent;

        String event = headers.get("x-gitlab-event").toString();

        logger.info("Event:-" + gitLabData.getObject_kind());
        logger.info("GitLab Data:-" + gitLabData);
        try {
            if (gitLabData == null) {
                throw new DataNotFoundException("Unable to get the gitlab event data");
            } else {
                actorId = gitLabData.getUser_username();
                actorType = "user";
                actorProvider = "gitlab";
                verb = gitLabData.getObject_kind();
                if (event.equals("Push Hook")) {
                    objectType = "commit";
                    objectContent = (String) gitLabData.getProject().get("web_url");
                } else if (event.equals("Issue Hook")) {
                    actorId = (String) gitLabData.getUser().get("username");
                    objectType = (String) gitLabData.getObject_attributes().get("action");
                    objectContent = (String) gitLabData.getProject().get("web_url");
                }
                targetType = "repository";
                targetContent = (String) gitLabData.getRepository().get("homepage");
                /*Transformer:-
                Converting the GithubData to ActivityStream*/
                ActivityStream as = new ActivityStream(actorId, actorType, actorProvider, verb, objectType, objectContent,
                        targetType, targetContent);
                Activity activity = as.getActivity();/*
                logger.info(activity);
                /*Library to read ActivityStream data to Java Objects
                Converting the ActivityStream data to Java Objects*/
                JavaObjects javaObjectsLab = new JavaObjects();
                javaObjectsLab.setActivity(activity);
                userObject = javaObjectsLab.getUserData();
            }
        } catch (DataNotFoundException errorMessage) {
            logger.error("Unable to get the gitlab data" + errorMessage);
        }
        return userObject;
    }
}
