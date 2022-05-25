package br.com.criptosys.domain.repository;

import br.com.criptosys.domain.entity.UserDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<UserDE, BigInteger> {
}
