package it.paolomolteni.genericcrud;

public class CrudException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6075095443924885232L;

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CrudException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CrudException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param args
	 */
	public CrudException(String message, Object ...args) {
		super(String.format(message, args));
	}

	/**
	 * @param message
	 */
	public CrudException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CrudException(Throwable cause) {
		super(cause);
	}

}
