package dtu.example.Service;

public class ServiceResult<T> {
    // Generic class to handle service results
    // Made because it was tricky to return multiple project statuses
    // and error messages from the service layer without using exceptions
    // TODO: If we have time, refactor other StatusMessage resuls to use this class
    private final boolean success;
    private final T data;
    private final String errorMessage;

    private ServiceResult(boolean success, T data, String errorMessage) {
        this.success = success;
        this.data = data;
        this.errorMessage = errorMessage;
    }
    public static <T> ServiceResult<T> success(T data) {
        return new ServiceResult<>(true, data, null);
    }
    public static <T> ServiceResult<T> error(String errorMessage) {
        return new ServiceResult<>(false, null, errorMessage);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}