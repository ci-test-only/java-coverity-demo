package com.example.vendor;

import java.sql.*;

// Third-party code that we want excluded from analysis via coverity.yaml exclude-regex.
// It has the same types of defects as src/ but shouldn't show up in Polaris.
public class ThirdPartyHelper {
    public void legacyLogin(Connection conn, String u, String p) throws SQLException {
        String sql = "SELECT * FROM legacy WHERE name='" + u + "' AND key='" + p + "'";
        try (Statement s = conn.createStatement()) {
            s.executeQuery(sql);
        }
    }
}
