package com.metrix.parser.transformer;

import com.metrix.libs.model.UserData;

import java.util.HashMap;

public interface ActivityStreamTransformer {

    UserData convertToActivityStream(HashMap<String, Object> userdata, HashMap<String, String> headers);

}
