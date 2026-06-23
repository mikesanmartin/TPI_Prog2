package entities;

import java.time.LocalDateTime;

public abstract class Base {
    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;
    
    public Base(Long id, boolean eliminado, LocalDateTime createdAt){
        setId(id);
        setEliminado(eliminado);
        setCreatedAt(createdAt);
    }

    public void setId(Long id) {
        if(id == null){
            throw new IllegalArgumentException("ID no puede ser nulo.");
        }
        if(id <= 0){
            throw new IllegalArgumentException("ID debe ser un número mayor a cero.");
        }
        this.id = id;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public abstract String toString();
}
