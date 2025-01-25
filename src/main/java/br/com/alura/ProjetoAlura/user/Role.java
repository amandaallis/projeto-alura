package br.com.alura.ProjetoAlura.user;

public enum Role {
    STUDENT("STUDENT"),
    INSTRUCTOR("INSTRUCTOR");

    private String role;

     Role(String role) {
        this.role = role;
    }

    public String getRole() {
         return role;
    }
}
