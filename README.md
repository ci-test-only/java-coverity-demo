# java-coverity-demo

Minimal Java project for **Polaris SAST** demo via Jenkins + Bridge CLI, featuring a working `coverity.yaml`.

## Structure

```
src/main/java/com/example/   - In-scope application code (intentional defects)
vendor/                       - Third-party code (EXCLUDED via coverity.yaml exclude-regex)
coverity.yaml                 - Polaris SAST config (buildless mode)
```

## Intentional defects

- `UserInput.java`: SQL_INJECTION, PATH_MANIPULATION, FORWARD_NULL
- `ResourceMgr.java`: RESOURCE_LEAK, WEAK_PASSWORD_HASH (MD5), HARDCODED_CREDENTIALS
- `vendor/ThirdPartyHelper.java`: SQL_INJECTION (excluded from analysis)

## Scanned by

- Jenkins pipeline: `polaris-java-coverity-yaml`
- Local CLI: `/home/allenl/demo/polaris/06_java_coverity_yaml/run.sh`
