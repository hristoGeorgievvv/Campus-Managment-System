package nl.tudelft.oopp.demo.repositories;

import java.util.List;

import nl.tudelft.oopp.demo.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE userName = :username", nativeQuery = true)
    List<User> findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<String> getUser(String userName);

    User getUserById(Long id);

    User getByUserName(String userName);

    @Query(value = "SELECT * FROM users WHERE userName = :username", nativeQuery = true)
    User findUser(@Param("username") String username);

    @Query(value = "SELECT type FROM users WHERE userName = :username", nativeQuery = true)
    String findTypeUsername(@Param("username") String username);

    @Query(value = "SELECT email FROM users WHERE userName = :username", nativeQuery = true)
    String findEmailUsername(@Param("username") String username);

    @Query(value = "SELECT id FROM users WHERE userName = :username", nativeQuery = true)
    String findidUsername(@Param("username") String username);

    @Query(value = "SELECT id FROM users WHERE userName = :username", nativeQuery = true)
    Long findidUsernameLong(@Param("username") String username);

    @Query(value = "SELECT userName FROM users WHERE userName = :username", nativeQuery = true)
    String findNameUsername(@Param("username") String username);

    @Query(value = "SELECT bike FROM users WHERE userName = :username", nativeQuery = true)
    boolean hasBike(@Param("username") String username);

    @Modifying
    @Query("UPDATE User u SET u.type = :type where u.userName = :userName")
    void setUUser(@Param("type") String type, @Param("userName") String userName);

    @Modifying/*(clearAutomatically = true, flushAutomatically = true)*/
    @Query("UPDATE User u SET u.bike = :bool where u.userName = :userName")
    void setUserBike(@Param("bool") Boolean bool, @Param("userName") String userName);


}