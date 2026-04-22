package com.example;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

/** Intentional defects for Polaris SAST demo. */
public class UserInput {

    // TAINTED_SCALAR + SQL_INJECTION: untrusted input concatenated into SQL
    public void login(Connection conn, String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username='" + username
                   + "' AND password='" + password + "'";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Hello " + rs.getString("username"));
            }
        }
    }

    // PATH_MANIPULATION: user input used directly as file path
    public String readFile(String name) throws IOException {
        File f = new File(name);
        try (FileReader r = new FileReader(f)) {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = r.read()) != -1) sb.append((char) c);
            return sb.toString();
        }
    }

    // FORWARD_NULL: dereference after possible null return
    public int length(String s) {
        String lower = s.toLowerCase();
        if ("admin".equals(lower)) {
            s = null;
        }
        return s.length();
    }

    public static void main(String[] args) throws Exception {
        UserInput u = new UserInput();
        Scanner sc = new Scanner(System.in);
        String uname = sc.nextLine();
        String pwd = sc.nextLine();
        u.login(null, uname, pwd);
    }
}
