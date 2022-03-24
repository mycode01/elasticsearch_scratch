package com.example.demo.search.persist.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceRepo extends JpaRepository<Space, Long> {

  List<Space> findBySpaceIdIn(List<String> spaceId);
}
