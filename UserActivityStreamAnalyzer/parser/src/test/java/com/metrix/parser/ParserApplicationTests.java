package com.metrix.parser;
import com.metrix.libs.LibsApplication;
import com.metrix.parser.activitystream.ActivityStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibsApplication.class)
@TestPropertySource("/application-test.properties")
public class ParserApplicationTests {

    @Test
    @DisplayName("Test Activity Pipeline")
    public void testActivityStream() throws Exception {
        ActivityStream activityStream = new ActivityStream("harshgarg0906", "user", "github", "commit",
                "https://github.com/harshgarg0906/Test/commit/350ec7a0f3a6734e218895eb417ccdb4a6a8ae94",
                "commit", "repository", "https://github.com/harshgarg0906/Test");
        assertEquals(activityStream.getActorId(), "harshgarg0906");
        assertEquals(activityStream.getActorType(), "user");
        assertEquals(activityStream.getActorProvider(), "github");
        assertEquals(activityStream.getVerb(), "commit");
        assertEquals(activityStream.getObjectType(), "https://github.com/harshgarg0906/Test/commit/350ec7a0f3a6734e218895eb417ccdb4a6a8ae94");
        assertEquals(activityStream.getObjectContent(), "commit");
        assertEquals(activityStream.getTargetType(), "repository");
        assertEquals(activityStream.getTargetContent(), "https://github.com/harshgarg0906/Test");
    }

}
