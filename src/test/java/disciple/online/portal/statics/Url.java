package disciple.online.portal.statics;

public final class Url {

    private Url(){}

    private static final String HOST_DEVELOPMENT = "http://localhost:8080/";
    private static final String LOGINPAGE = "login";

    public static String getUrlLoginpage(){
        return HOST_DEVELOPMENT + LOGINPAGE;
    }
    public static String getCurrentHost(){return HOST_DEVELOPMENT;}
}
