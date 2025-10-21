package ar.com.avaco.ws.rest.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LogManager.getLogger(RestResponseEntityExceptionHandler.class);

	public RestResponseEntityExceptionHandler() {
		super();
	}

	// =====================
	// 400 - BAD REQUEST
	// =====================
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {
		logger.warn("Data integrity violation at {}: {}", request.getDescription(false), ex.getMessage(), ex);
		final String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.warn("Malformed JSON request at {}: {}", request.getDescription(false), ex.getMessage(), ex);
		final String bodyOfResponse = "Invalid or unreadable JSON in request body";
		return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.warn("Validation failed for request {}: {}", request.getDescription(false), ex.getMessage(), ex);
		final String bodyOfResponse = "Validation failed for request parameters";
		return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
	}

	// =====================
	// 401 - UNAUTHORIZED
	// =====================
	@ExceptionHandler({ UsernameNotFoundException.class })
	public ResponseEntity<Object> handleAuthentication(final Exception ex, final WebRequest request) {
		logger.info("Unauthorized access attempt: {}", request.getDescription(false), ex);
		return new ResponseEntity<>("Invalid token", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	}

	// =====================
	// 403 - FORBIDDEN
	// =====================
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(final Exception ex, final WebRequest request) {
		logger.warn("Access denied: {} - {}", request.getDescription(false), ex.getMessage(), ex);
		return new ResponseEntity<>("Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
	}

	// =====================
	// 409 - CONFLICT
	// =====================
	@ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
	protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
		logger.error("Data access conflict at {}: {}", request.getDescription(false), ex.getMessage(), ex);
		final String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	// =====================
	// 500 - INTERNAL SERVER ERROR
	// =====================
	@ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
	public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
		// 🔴 Log completo con stacktrace y detalle del request
		logger.error("Internal server error at {}: {}", request.getDescription(false), ex.getMessage(), ex);
		final String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}
}
