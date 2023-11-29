package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository ROLE_REPOSITORY;//todo: ..указывалось

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.ROLE_REPOSITORY = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getListOfRoles() {
        return ROLE_REPOSITORY.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return ROLE_REPOSITORY.getOne(id);
    }

    @Override
    @Transactional//todo: выносим над классом типовой случай
    public void saveRole(Role role) {
        ROLE_REPOSITORY.save(role);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        ROLE_REPOSITORY.save(role);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        ROLE_REPOSITORY.deleteById(id);
    }
}