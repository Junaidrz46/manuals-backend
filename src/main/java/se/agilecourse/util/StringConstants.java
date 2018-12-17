package se.agilecourse.util;

import org.springframework.stereotype.Component;

@Component
public class StringConstants {

    public final String LOGIN_SUCCESS = "login-success";
    public final String LOGIN_FAIL = "login-fail";

    /*
     * Added following rolese to make rolese standered throught system
     */

    public final String ROLE_ADMIN="ADMIN";
    public final String ROLE_REPRESANTATIVE="REPRESENTATIVE";
    public final String ROLE_CONSUMER="CONSUMER";

}
