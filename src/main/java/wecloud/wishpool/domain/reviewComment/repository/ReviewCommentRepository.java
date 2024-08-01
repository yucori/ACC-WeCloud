package wecloud.wishpool.domain.reviewComment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wecloud.wishpool.domain.reviewComment.entity.ReviewComment;

import java.util.List;
import java.util.Optional;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment,Long> {
    List<ReviewComment> findByReviewId(Long reviewId);
    Optional<ReviewComment> findByIdAndIsDeletedFalse(Long reviewCommentId);
}
