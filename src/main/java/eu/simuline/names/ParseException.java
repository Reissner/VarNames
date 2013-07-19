package eu.simuline.names;

/**
 * Describe class ParseException here.
 *
 *
 * Created: Fri Jul 19 22:49:41 2013
 *
 * @author <a href="mailto:ernst@">Ernst Reissner</a>
 * @version 1.0
 */
public class ParseException extends RuntimeException {
    private static final long serialVersionUID = -2479143000061671589L;
    ParseException(String msg) {
	super(msg);
    }
}


