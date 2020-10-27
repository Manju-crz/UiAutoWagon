package mks.uiautowagon.exceptions;

public class NoSuchElementPositionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoSuchElementPositionException(String message) {
		super(message);
	}

	public NoSuchElementPositionException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchElementPositionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoSuchElementPositionException(Throwable cause) {
		super(cause);
	}
}
