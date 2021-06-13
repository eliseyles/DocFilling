package by.bntu.kharaneka.enrolleedocfillingmvp.entity;

public enum Permission {
    WRITE("write"),
    READ("read"),
    ADMIN_READ("admin:read");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
