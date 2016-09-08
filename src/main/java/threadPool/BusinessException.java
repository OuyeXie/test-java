package threadPool;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 638336348902251497L;
	private String errorCode;
	private String errorMsg;

	public BusinessException() {
		super();
	}

	public BusinessException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public final String getMessage() {
		return String.format("{ \"%s\": \"%s\", \"%s\": \"%s\" }", "ErrCode",
				errorCode, "ErrMsg", errorMsg);
	}

	public final int getCode() {
		return 200;
	}
}
