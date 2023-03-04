package pe.com.pargasys.backend.constant;

public class Constant {

    private Constant() {
        throw new IllegalStateException("Constant class");
    }

    public static final String NF_COMPANY = "Compañia no encontrada.";
    public static final String NF_TRACKING = "Pedido no encontrado.";
    public static final String CHARGE_STATUS = "1";

    /* Response */
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
