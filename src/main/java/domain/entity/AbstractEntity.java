package domain.entity;

public abstract class AbstractEntity {
    Object getId() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) return false;
        return this.getId().equals(((AbstractEntity) obj).getId());
    }
}
