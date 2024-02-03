package com.cluster2.ajosavingscluster2.utils;

public class Template {
    public static String passwordResetEmail(String name, String token, String link) {
        return "<div style=\"font-family: Helvetica, Arial, sans-serif; font-size: 16px; margin: 0; color: #0b0c0c;\">\n" +
                "<p>Hello " + name + ",</p>\n" +
                "<p>You have requested to reset your GoCash account password. Please click on the link below to reset your password:</p>\n" +
                "<p style=\"padding: 10px; background-color: #f5f5f5; border: 1px solid #ddd; border-radius: 5px; word-wrap: break-word;\">\n" +
                "<a href=\"" + Template.passwordResetLink(token, link) + "\">" + Template.passwordResetLink(token, link) + "</a>\n" +
                "</p>\n" +
                "<p>If you did not request a password reset, you can ignore this email.</p>\n" +
                "<p>Thank you,</p>\n" +
                "<p>The GoCash Team</p>\n" +
                "</div>";
    }
    public static String passwordResetLink(String token, String link) {
        return   link + token;
    }
}
