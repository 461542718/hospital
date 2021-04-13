package com.dejie.hospital.registration.repositories;

import com.dejie.hospital.registration.entity.department;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

@Primary
public interface DepartmentRepository extends ElasticsearchRepository<department,Long> {
    List<department> findByDepartmentname(String Departmentname, Pageable pageable);
}
