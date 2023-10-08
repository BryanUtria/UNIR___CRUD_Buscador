package com.bscu.crud.UsersBD.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bscu.crud.UsersBD.Entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    public User findByName(String valor);

    @Query(value = "select * from users where id = :valor OR name like %:valor% OR last_name like"+
    " %:valor% OR age like %:valor% OR email like %:valor% OR number_phone like %:valor%", 
    nativeQuery = true)
    public List<User> findByValor(@Param("valor") String valor);
}
