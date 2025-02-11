package com.springSecurity.payload;

public class LoginDTO {

    private  String userName;
    private String passdWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassdWord() {
        return passdWord;
    }

    public void setPassdWord(String passdWord) {
        this.passdWord = passdWord;
    }
}
