package mks.uiautowagon.exceptions;

public class WagonInteractorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WagonInteractorException(String message) {
		super(message);
	}

	public WagonInteractorException(String message, Throwable cause) {
		super(message, cause);
	}

	public WagonInteractorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WagonInteractorException(Throwable cause) {
		super(cause);
	}

}
