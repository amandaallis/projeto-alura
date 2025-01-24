package br.com.alura.ProjetoAlura.user;

public enum Role {
    STUDENT("student"),
    INSTRUCTOR("instructor");

    private String role;

     Role(String role) {
        this.role = role;
    }

    public String getRole() {
         return role;
    }
}
