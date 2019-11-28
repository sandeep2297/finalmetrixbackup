package com.metrix.usermicroservice.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.metrix.usermicroservice.model.User;
import com.metrix.usermicroservice.model.User.userRole;
import com.metrix.usermicroservice.service.IGitlabService;
import com.metrix.usermicroservice.service.IGoogleService;
import com.metrix.usermicroservice.service.IUserService;
import com.metrix.usermicroservice.util.CookieUtil;
import com.metrix.usermicroservice.util.JwtUtil;

@Controller
@RequestMapping("api/v1")
public class AuthController {

    @Value("${gitlab.base.url}")
    private String baseUrl;

    @Value("${spring.security.oauth2.client.registration.gitlab.client-id}")
    String clientId;

    @Value("${spring.security.oauth2.client.registration.gitlab.client-secret}")
    String clientSecret;

    @Value("${spring.security.oauth2.client.registration.gitlab.redirect-uri}")
    String redirectUrl;

    @Value("${client_dashboard_url}")
    String clientDashboardUrl;

    @Value("${admin_dashboard_url}")
    String adminDashboardUrl;

    @Value("${Domain}")
    String domain;

    private static final String SYSTEM = "SYSTEM";

    @Autowired
    private IGoogleService googleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGitlabService gitlabService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String jwtTokenCookieName = "JWT-TOKEN";

    // Redirect to login/consent form on Google's authentication page
    @GetMapping(value = "/googlelogin")
    public RedirectView googlelogin() {
        RedirectView redirectview = new RedirectView();
        String url = googleService.googlelogin();
        redirectview.setUrl(url);
        return redirectview;
    }

    // Redirect to login/consent form on Gitlab authentication page
    @GetMapping("/gitlablogin")
    public RedirectView gitlablogin() {
        String url = baseUrl + "?client_id=" + this.clientId + "&redirect_uri=" + this.redirectUrl
                + "&response_type=code&scope=read_user+api";
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }

    // Gitlab calls back on user's successful authentication and consent
    @GetMapping("/admindashboard")
    public RedirectView getAdminDetails(@RequestParam("code") String code, HttpServletResponse response)
            throws IOException {
        String accessToken = gitlabService.getAccessToken(code);
        JSONObject userDetails = gitlabService.getUserProfile(accessToken);
        User user = new User();
        user.setAvatarURL((String) userDetails.get("avatar_url"));
        user.setEmail((String) userDetails.get("email"));
        user.setUserName((String) userDetails.get("username"));
        user.setName((String) userDetails.get("name"));
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy(SYSTEM);
        user.setUpdatedAt(LocalDateTime.now());
        user.setUpdatedBy(SYSTEM);
        user.setRead(false);
        RedirectView redirectview = new RedirectView();
        if (gitlabService.getUserGroups(accessToken)) {
            user.setRole(userRole.SUPERADMIN);
            String jwtToken = JwtUtil.addToken(response, user);
            CookieUtil.create(response, jwtTokenCookieName, jwtToken, false, -1, domain);
            try {
                userService.addUserData(user);
            } catch (DuplicateKeyException exception) {
                logger.error("In getAdminDetails method " + LocalDateTime.now() + " " + exception.getMessage());
            } catch (Exception exception) {
                logger.error("In getAdminDetails method " + LocalDateTime.now() + " " + exception.getMessage());
            }
            redirectview.setUrl(adminDashboardUrl);
            return redirectview;
        } else {
            user.setRole(userRole.CLIENT);
            String jwtToken = JwtUtil.addToken(response, user);
            CookieUtil.create(response, jwtTokenCookieName, jwtToken, false, -1, domain);
            try {
                userService.addUserData(user);
            } catch (DuplicateKeyException exception) {
                logger.error("In getAdminDetails method " + LocalDateTime.now() + " " + exception.getMessage());
            } catch (Exception exception) {
                logger.error("In getAdminDetails method " + LocalDateTime.now() + " " + exception.getMessage());
            }
            redirectview.setUrl(clientDashboardUrl);
            return redirectview;
        }

    }

    // Google calls back on user's successful authentication and consent
    @GetMapping(value = "/clientdashboard")
    public RedirectView google(@RequestParam("code") String code, HttpServletResponse res) {
        String accessToken = googleService.getGoogleAccessToken(code);
        User user = googleService.getGoogleUserProfile(accessToken);
        String jwtToken = JwtUtil.addToken(res, user);
        CookieUtil.create(res, jwtTokenCookieName, jwtToken, false, -1, domain);
        RedirectView redirectview = new RedirectView();
        try {
            userService.addUserData(user);
        } catch (DuplicateKeyException exception) {
            logger.error("In google method " + LocalDateTime.now() + " " + exception.getMessage());
        } catch (Exception exception) {
            logger.error("In google method " + LocalDateTime.now() + " " + exception.getMessage());
        }
        redirectview.setUrl(clientDashboardUrl);
        return redirectview;
    }

    // For logging out the user form the system
    @GetMapping(value = "/userlogout")
    public void googleLogout(HttpServletResponse response) {
        String cookiename = jwtTokenCookieName;
        CookieUtil.clearCookie(response, cookiename);
    }

    // For logging out the user form the system
    @PatchMapping(value = "/update")
    public ResponseEntity updateUserData(@RequestParam("email") String email, @RequestBody String body) {
        return ResponseEntity.ok(userService.patchUserData(email));
    }

}
