package com.metrix.parser.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.common.activitystreams.Activity;
import com.metrix.libs.model.Github;
import com.metrix.libs.model.UserData;
import com.metrix.parser.activitystream.ActivityStream;
import com.metrix.parser.activitystream.JavaObjects;
import com.metrix.parser.exception.DataNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class GithubTransformer implements ActivityStreamTransformer {

    public Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public UserData convertToActivityStream(HashMap<String, Object> userdata, HashMap<String, String> headers) {
        ObjectMapper mapper = new ObjectMapper();
        Github hubData = mapper.convertValue(userdata, Github.class);

        UserData userObject =null;
        String actorId;
        String actorType;
        String actorProvider;
        String verb;
        String objectType = null;
        String objectContent = null;
        String targetType;
        String targetContent;

        String event = headers.get("x-github-event").toString();

        logger.info("Event-" + event);
        logger.info("GitHUB Data" + hubData);
        //Getting data from GithubTransformer
        try {
            if (hubData == null) {
                throw new DataNotFoundException("Unable to get the github event data");
            } else {
                actorId = (String) hubData.getSender().get("login");
                actorType = (String) hubData.getSender().get("type");
                actorProvider = "github";
                verb = event;
                if (event.equals("push")) {
                    objectContent = (String) hubData.getHead_commit().get("url");
                    objectType = "commit";
                } else if (event.equals("create")) {
                    objectContent = hubData.getRef();
                    objectType = hubData.getRef_type();
                } else if (event.equals("issues")) {
                    objectContent = (String) hubData.getIssue().get("url");
                    objectType = hubData.getAction();
                }
                targetType = "repository";
                targetContent = (String) hubData.getRepository().get("html_url");
                //Converting the GithubData to ActivityStream
                ActivityStream as = new ActivityStream(actorId, actorType, actorProvider, verb, objectType, objectContent,
                        targetType, targetContent);
                Activity activity = as.getActivity();
                logger.info(activity);
                /*Library to read ActivityStream data to Java Objects
                Converting the ActivityStream data to Java Objects*/
                JavaObjects javaObjects = new JavaObjects();
                javaObjects.setActivity(activity);
                userObject = javaObjects.getUserData();
            }

        } catch (DataNotFoundException e) {
            logger.error("Unable to get the data from source");
        }
        return userObject;
    }
}