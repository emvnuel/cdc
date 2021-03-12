package io.github.emanuelcerqueira.cdc.common;

import io.github.emanuelcerqueira.cdc.common.exceptions.BusinessException;
import io.github.emanuelcerqueira.cdc.common.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractService<Entity, Repository extends JpaRepository<Entity, Long>> {

    @Autowired
    protected Repository repository;

    @Transactional(readOnly = true)
    public List<Entity> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Entity> findAll(Integer page, Integer linesPerPage, String orderBy, String direction) {
        Pageable pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    public Entity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("The resource with the given id does not exist"));
    }

    @Transactional
    public Entity save(Entity entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Transactional
    public List<Entity> save(Iterable<Entity> entities) {
        try {
            return repository.saveAll(entities);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            findById(id);
            repository.deleteById(id);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Transactional
    public void delete(List<Entity> entities) {
        try {
            repository.deleteAll(entities);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

}
