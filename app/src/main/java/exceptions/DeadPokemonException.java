package exceptions;

/**
 * Created by Lukasz on 2017-08-07.
 */
public class DeadPokemonException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DeadPokemonException() {
        // TODO Auto-generated constructor stub
    }

    public DeadPokemonException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public DeadPokemonException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public DeadPokemonException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public DeadPokemonException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
