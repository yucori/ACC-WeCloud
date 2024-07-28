package wecloud.wishpool.domain.wish.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wecloud.wishpool.domain.wish.dto.request.WishSaveRequestDto;
import wecloud.wishpool.domain.wish.service.WishService;
import wecloud.wishpool.global.response.ApiResponse;

@RestController
@RequestMapping("/wishes")
@RequiredArgsConstructor
public class WishController {
    private final WishService wishService;
    @PostMapping
    public ApiResponse<Long> createWish(@RequestBody WishSaveRequestDto requestDto) {
        return ApiResponse.response201Success(wishService.createWish(requestDto), "소원 피드 생성");
    }

}
