package org.sso.sso.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

  @GetMapping("")
  public String index(Authentication user) {
    if (user.getPrincipal() instanceof OAuth2User oauth2User) {
      String name = oauth2User.getAttribute("name");
      String email = oauth2User.getAttribute("email");
      return "Hello user " + name + ", your email is " + email;
    }
    return "Hello user " + user.getPrincipal();
  }

}
