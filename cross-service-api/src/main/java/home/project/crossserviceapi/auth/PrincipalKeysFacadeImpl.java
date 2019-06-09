package home.project.crossserviceapi.auth;

public class PrincipalKeysFacadeImpl implements PrincipalKeysFacade{
    private static final String FIRST_NAME = "first_name";
    private static final String MIDDLE_NAME = "middle_name";
    private static final String LAST_NAME = "last_name";
    private static final String EXTERNAL_DATA = "external_data";
    private static final String[] PRINCIPAL_KEYS = new String[]{"user", "username",
            "userid", "user_id", "login", "id", "name",
            FIRST_NAME, MIDDLE_NAME, LAST_NAME, EXTERNAL_DATA};

    @Override
    public String[] getPrincipalKeys() {
        return PRINCIPAL_KEYS;
    }
}
