package se.agilecourse.util;

import org.springframework.stereotype.Component;

@Component
public class StringConstants {

    public final String LOGIN_SUCCESS = "login-success";
    public final String LOGIN_FAIL = "login-fail";

    /*
     * Added following rolese to make rolese standered throught system
     */

    public final String ROLE_ADMIN="companyAdmin";
    public final String ROLE_REPRESANTATIVE="companyRepresentative";
    public final String ROLE_CONSUMER="customer";

}
