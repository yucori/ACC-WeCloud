package wecloud.wishpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WishPoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(WishPoolApplication.class, args);
    }

}
