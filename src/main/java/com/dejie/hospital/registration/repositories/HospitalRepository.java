package com.dejie.hospital.registration.repositories;

import com.dejie.hospital.registration.entity.hospital;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface HospitalRepository extends ElasticsearchRepository<hospital,Long> {
    List<hospital> findByHname(String Hname, Pageable pageable);
}
