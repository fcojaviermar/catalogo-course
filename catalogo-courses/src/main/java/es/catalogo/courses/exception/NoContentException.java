package es.catalogo.courses.exception;

public final class NoContentException extends RuntimeException {

	private static final long serialVersionUID = -6483997690346845129L;

	
	public NoContentException(String message) {
		super(message);
		
	}
}
