package by.bntu.kharaneka.enrolleedocfillingmvp.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    USER(Stream.of(Permission.READ).collect(Collectors.toSet())),
    ADMIN(Stream.of(Permission.READ, Permission.WRITE, Permission.ADMIN_READ).collect(Collectors.toSet()));

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    private final Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
