package wecloud.wishpool.domain.wish.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wecloud.wishpool.domain.funding.entity.Funding;
import wecloud.wishpool.domain.review.entity.Review;
import wecloud.wishpool.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_id")
    private Long id;
    private String title;
    private String content;
    private String image;
    private LocalDateTime deadline;
    private Long targetAmount;
    private Long currentAmount;
    private boolean isDeleted;

    @OneToOne(mappedBy = "wish")
    private Review review;

    @OneToMany(mappedBy = "wish")
    private List<Funding> fundings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
