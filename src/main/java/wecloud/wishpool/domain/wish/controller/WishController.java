package wecloud.wishpool.domain.wish.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wecloud.wishpool.domain.wish.dto.request.WishSaveRequestDto;
import wecloud.wishpool.domain.wish.dto.request.WishUpdateRequestDto;
import wecloud.wishpool.domain.wish.dto.response.WishGetOneResponseDto;
import wecloud.wishpool.domain.wish.entity.Wish;
import wecloud.wishpool.domain.wish.service.WishService;
import wecloud.wishpool.global.aws.service.S3Service;
import wecloud.wishpool.global.response.ApiResponse;

import java.io.IOException;

@Tag(name = "Wish API", description = "소원 피드 API")
@RestController
@RequestMapping("/wishes")
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;
    private final S3Service s3Service;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "소원 피드 생성", description = "소원 피드를 생성합니다.")
    public ApiResponse<Long> createWish(@RequestParam Long userId,
                                        @RequestPart(value = "img", required = false) MultipartFile multipartFile,
                                        @RequestPart WishSaveRequestDto requestDto) throws IOException {
        Wish wish = wishService.createWish(userId, requestDto);
        String fileName = "";

        if(multipartFile != null){ // 파일 업로드한 경우에만

            try{// 파일 업로드
                fileName = s3Service.upload(multipartFile, "wish"); // S3 버킷의 images 디렉토리 안에 저장됨
                System.out.println("fileName = " + fileName);
            }catch (IOException e){
                return (ApiResponse<Long>) ApiResponse.response400Error("파일 업로드 실패");
            }
        }
        wish.updateImage(fileName);
        return ApiResponse.response201Success(wish.getId(), "소원 피드 생성 완료");
    }

    @GetMapping("/{wishId}")
    @Operation(summary = "소원 피드 조회", description = "소원 피드를 조회합니다.")
    public ApiResponse<WishGetOneResponseDto> getWish(@PathVariable Long wishId) {
        WishGetOneResponseDto wishDto = wishService.findById(wishId);
        return ApiResponse.responseSuccess(wishDto, "소원 피드 조회 완료");
    }

    @PutMapping("/{wishId}")
    @Operation(summary = "소원 피드 수정", description = "소원 피드를 수정합니다.")
    public ApiResponse<WishGetOneResponseDto> updateWish(@PathVariable Long wishId, @RequestBody WishUpdateRequestDto requestDto) {
        WishGetOneResponseDto wishDto = wishService.updateWish(wishId,requestDto);
        return ApiResponse.responseSuccess(wishDto, "소원 피드 수정 완료");
    }

    @DeleteMapping("/{wishId}")
    @Operation(summary = "소원 피드 삭제", description = "소원 피드를 삭제합니다.")
    public ApiResponse<?> deleteWish(@PathVariable Long wishId) {
        wishService.deleteWish(wishId);
        return ApiResponse.responseSuccess("소원 피드 삭제 완료");
    }



}
