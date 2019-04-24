package com.rina.learn.jm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rina.learn.jm.entity.AppEntity;

@Repository
@Transactional
public interface AppEntityRepository extends JpaRepository<AppEntity, String>, QuerydslPredicateExecutor<AppEntity>{

}
