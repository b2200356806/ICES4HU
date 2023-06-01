package penguins.backend.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByUserId(Integer userId);


    @Query(value = """
      select  u from User u 
      where u.username = :username 
      """)
    Optional<User> findUserIDByUserName(String username);

    @Query(value = """
      select  u from User u 
      where u.username = :username
      """)
    Optional<User> findUserTypeByUserName(String username);


}
