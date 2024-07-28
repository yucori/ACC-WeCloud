package wecloud.wishpool.global.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse<T> {

    private boolean isSuccess;
    private StatusEnum status;
    private T data;
    private String message;

    public static <T> ApiResponse<T> responseSuccess(T data) {
        return new ApiResponse<>(true, StatusEnum.OK, data, null);
    }

    public static <T> ApiResponse<T> responseSuccess(T data, String message) {
        return new ApiResponse<>(true, StatusEnum.OK, data, message);
    }

    @Builder
    private ApiResponse(boolean isSuccess, StatusEnum status, T data, String message) {
        this.isSuccess = isSuccess;
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
