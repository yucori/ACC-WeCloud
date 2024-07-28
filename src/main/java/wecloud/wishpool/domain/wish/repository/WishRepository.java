package wecloud.wishpool.domain.wish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wecloud.wishpool.domain.wish.entity.Wish;

public interface WishRepository extends JpaRepository<Wish,Long> {
}
