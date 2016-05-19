package worthing;

/**
 * Product Not Found Exception
 */
public class ProuctNotFoundException extends  RuntimeException {

    public ProuctNotFoundException() {
    }

    public ProuctNotFoundException(String message) {
        super(message);
    }
}
