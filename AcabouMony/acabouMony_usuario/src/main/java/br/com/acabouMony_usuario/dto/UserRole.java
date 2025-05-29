package br.com.acabouMony_usuario.dto;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public static UserRole getRole(String text){
       for (UserRole role1 : UserRole.values()){
           if (role1.role.equalsIgnoreCase(text)){
               return role1;
           }
       }

       throw new IllegalArgumentException("Role não encontrado!");
    }


}
