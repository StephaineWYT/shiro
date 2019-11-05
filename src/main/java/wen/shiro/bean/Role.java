package wen.shiro.bean;

import java.util.Set;

public class Role {

    private Long id;
    private String role;
    private Set<Permission> permissions;

    public Role(Long id, String role, Set<Permission> permissions) {
        this.id = id;
        this.role = role;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
