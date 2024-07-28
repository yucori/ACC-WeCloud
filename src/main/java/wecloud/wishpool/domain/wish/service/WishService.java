package wecloud.wishpool.domain.wish.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wecloud.wishpool.domain.wish.dto.request.WishSaveRequestDto;
import wecloud.wishpool.domain.wish.entity.Wish;
import wecloud.wishpool.domain.wish.repository.WishRepository;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;

    public Long createWish(WishSaveRequestDto requestDto) {
        return wishRepository.save(toEntity(requestDto)).getId();
    }

    private Wish toEntity(WishSaveRequestDto requestDto) {
        return Wish.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .image(requestDto.getImage())
                .deadline(requestDto.getDeadline())
                .targetAmount(requestDto.getTargetAmount())
                .build();
    }

}
