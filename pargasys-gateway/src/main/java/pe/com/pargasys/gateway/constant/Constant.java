package pe.com.pargasys.gateway.constant;

public class Constant {

    private Constant() {
        throw new IllegalStateException("Constant class");
    }

    public static final String SPACE = " ";
    public static final String BEARER = "Bearer";
    public static final String BR_FILTER = "Token inválido.";

    public static final Integer CODE_OK = 200;
    public static final Integer CODE_NO_CONTENT = 204;
    public static final Integer CODE_BAD_REQUEST = 400;
    public static final Integer CODE_NOT_FOUND = 404;
    public static final Integer CODE_SERVER_ERROR = 500;

    public static final String MESSAGE_OK = "OK";
    public static final String MESSAGE_NO_CONTENT = "NO CONTENT";
    public static final String MESSAGE_BAD_REQUEST = "BAD REQUEST";
    public static final String MESSAGE_NOT_FOUND = "NOT FOUND";
    public static final String MESSAGE_SERVER_ERROR = "SERVER ERROR";

}
