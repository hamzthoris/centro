package com.ctam.tcc.centro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ctam.tcc.centro.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long > {

}
