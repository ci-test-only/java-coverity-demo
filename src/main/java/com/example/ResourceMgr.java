package com.example;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ResourceMgr {

    // RESOURCE_LEAK: InputStream not closed on exception path
    public byte[] readAll(String file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        byte[] buf = new byte[4096];
        in.read(buf);   // may throw before close
        in.close();
        return buf;
    }

    // WEAK_PASSWORD_HASH: MD5 for credentials
    public String hashPassword(String pwd) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return bytesToHex(md.digest(pwd.getBytes()));
    }

    // HARDCODED_CREDENTIALS: password literal in source
    public boolean isAdmin(String pwd) {
        return "P@ssw0rd!2024-admin-demo".equals(pwd);
    }

    private String bytesToHex(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (byte x : b) sb.append(String.format("%02x", x));
        return sb.toString();
    }
}
