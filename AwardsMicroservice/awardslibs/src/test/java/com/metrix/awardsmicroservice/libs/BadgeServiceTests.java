package com.metrix.awardsmicroservice.libs;

import com.metrix.awardsmicroservice.libs.exception.BadgeNotFoundException;
import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.model.Alignment;
import com.metrix.awardsmicroservice.libs.model.Badge;
import com.metrix.awardsmicroservice.libs.service.IBadgeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AwardsLibsApplication.class)
@TestPropertySource("/application-test.properties")
public class BadgeServiceTests {

    @Autowired
    private IBadgeService badgeService;

    private static Badge badge;

    @BeforeAll
    public static void badgeClassSetup() {

        ArrayList<Alignment> alignments = new ArrayList<>();

        Alignment alignment1 = new Alignment();

        alignment1.setTargetName("Problem-Solving");
        alignment1.setTargetDescription("Follow precisely a complex " +
                "multi-step procedure when carrying out experiments," +
                " taking measurements, or performing technical tasks;" +
                " analyze the specific results based on explanations in the text.");
        alignment1.setTargetCode("CCSS.ELA-Literacy.RST.11-12.3");
        alignment1.setTargetFramework("Mozilla 21st Century Skills");

        alignments.add(alignment1);

        ArrayList<String> tags = new ArrayList<>();

        tags.add("awards");
        tags.add("robots");

        badge = new Badge("1","1", "101",
                "badge", "Awesome Robotics Badge",
                "For doing awesome things with robots that people think is pretty great.",
                "https://example.org/robotics-badge.png",
                "https://example.org/robotics-badge.html", alignments, tags, "OWNER", LocalDateTime.now()
                , "OwNER", LocalDateTime.now());
    }

    @BeforeEach
    public void saveData() {
        badgeService.saveBadge(badge);
    }

    @AfterEach
    public void deleteBadge() throws BadgeNotFoundException {
        badgeService.deleteByBadgeId(badge.getBadgeId());
    }

    @Test
    public void testPosting() throws DatabaseEmptyException {
        assertEquals(1, badgeService.findAllBadge().size());
    }

    @Test
    public void testGetBadgeDetails() throws BadgeNotFoundException {
        badge = badgeService.findByBadgeId(badge.getBadgeId());
        assertEquals("badge", badge.getType());
        assertNotNull(badge.getType());
        assertEquals("Awesome Robotics Badge", badge.getName());
        assertNotNull(badge.getName());
        assertEquals("For doing awesome things with robots that people think is pretty great.", badge.getDescription());
        assertNotNull(badge.getDescription());
        assertEquals("https://example.org/robotics-badge.png", badge.getImage());
        assertNotNull(badge.getImage());
        assertEquals("https://example.org/robotics-badge.html", badge.getCriteria());
        assertNotNull(badge.getCriteria());
    }

//    @Test
//    public void avoidDuplicate() {
//        Badge testBadge = badge;
//        Assertions.assertThrows(DuplicateKeyException.class, () -> {
//            badgeService.saveBadge(testBadge);
//        });
//    }

}