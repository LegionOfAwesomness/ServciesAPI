package com.project.shoponline.mail;

public enum SESFrom {

    ATTA("noreply@kewlwallet.com", "Kewlwallet"),
    NO_REPLY("noreply@kewlwallet.com", "My SES Test"),
    SUPPORT("support@kewlwallet.com", "My SES Support Support");

    private final String email;
    private final String name;

    private SESFrom(String email, String name) {
        this.email =email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
