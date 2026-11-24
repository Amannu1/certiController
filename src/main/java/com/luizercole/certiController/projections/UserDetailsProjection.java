package com.luizercole.certiController.projections;

public interface UserDetailsProjection {

    String getName();
    String getPassword();
    Long getRoleId();
    String getAuthority();
}
