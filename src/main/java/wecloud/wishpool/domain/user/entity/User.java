package wecloud.wishpool.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wecloud.wishpool.domain.funding.entity.Funding;
import wecloud.wishpool.domain.review.entity.Review;
import wecloud.wishpool.domain.reviewComment.entity.ReviewComment;
import wecloud.wishpool.domain.wish.entity.Wish;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String email;
    private String password;
    private Date birth;
    private boolean isDeleted;

    @OneToMany(mappedBy = "user")
    private List<Wish> wishes;

    @OneToMany(mappedBy = "user")
    private List<Funding> fundings;

    @OneToMany(mappedBy = "user")
    private List<ReviewComment> reviewComments;

}
