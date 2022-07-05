package br.com.criptosys.domain.repository;

import br.com.criptosys.domain.entity.UserDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<UserDE, BigInteger> {

    @Transactional(readOnly=true)
    UserDE findByEmail(String email);

    UserDE findByEmailAndUsername(String email, String username);

    UserDE findByUsername(String username);

}
