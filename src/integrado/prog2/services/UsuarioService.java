package services;

import java.util.List;
import java.util.ArrayList;

import entities.Usuario;

import enums.Rol;

public class UsuarioService {
    private final List<Usuario> usuarios;
    private Long ultimoId;

    public UsuarioService(){
        this.usuarios = new ArrayList<>();
        this.ultimoId = 0L;
    }

    public List<Usuario> listarUsuarios(){
        List<Usuario> usuariosActivos = new ArrayList<>();
        for(Usuario usuario:usuarios){
            if(!usuario.isEliminado()){
                usuariosActivos.add(usuario);
            }
        }
        return usuariosActivos;
    }

    public Usuario crearUsuario(String nombre, String apellido, String mail, String celular){
        for(Usuario usuario:usuarios){
            if(!usuario.isEliminado() && usuario.getMail().equalsIgnoreCase(mail.trim())){
                return null;
            }
        }

        ultimoId++;
        String contraseniaDefault = "ContraseniaACambiarDefault";
        Rol rolPorDefecto = Rol.USUARIO;
        Usuario nuevoUsuario = new Usuario(ultimoId,nombre,apellido,mail,celular,contraseniaDefault, rolPorDefecto);
        usuarios.add(nuevoUsuario);
        return nuevoUsuario;
    }

    public boolean editarUsuario(Long id, String nuevoNombre, String nuevoApellido, String nuevoMail, String nuevoCelular){
        for(Usuario usuario:usuarios){
            if(usuario.getId().equals(id) && !usuario.isEliminado()){
                usuario.setNombre(nuevoNombre);
                usuario.setApellido(nuevoApellido);
                usuario.setMail(nuevoMail);
                usuario.setCelular(nuevoCelular);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarUsuario(Long id){
        for(Usuario usuario:usuarios){
            if(usuario.getId().equals(id) && !usuario.isEliminado()){
                usuario.setEliminado(true);
                return true;
            }
        }
        return false;
    }

    public Usuario buscarUsuarioPorId(Long id){
        for(Usuario usuario:usuarios){
            if(usuario.getId().equals(id) && !usuario.isEliminado()){
                return usuario;
            }
        }
        return null;
    }
}
