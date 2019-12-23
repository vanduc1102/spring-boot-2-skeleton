package com.wizeline.skeleton.persistence.repository;

import com.wizeline.skeleton.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
