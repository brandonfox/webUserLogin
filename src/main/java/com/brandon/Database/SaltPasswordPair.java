package com.brandon.Database;

public class SaltPasswordPair {

    private byte[] salt;
    private byte[] password;

    public SaltPasswordPair(byte[] salt, byte[] password){
        this.salt = salt;
        this.password = password;
    }

    public byte[] getSalt() {return salt;}
    public byte[] getPassword(){ return password;}
}
