## Keystore steps

- Create a keystore

```shell
keytool -genkey -v -keystore ping2-joai-release-key.keystore -alias JEsperancinhaORG -keyalg RSA -keysize 2048 -validity 1000000
keytool -genkey -v -keystore ping2-joai-release-key.keystore -alias JEsperancinhaORG2 -keyalg RSA -keysize 2048 -validity 1000000
```

>Validity for 1000000

- Create private key


```shell
java -jar pepk.jar --keystore=ping2-joai-release-key.keystore --alias=key_ping_joai --output=encrypted_private_key_path --rsa-aes-encryption --encryption-key-path=encryption_public_key.pem
java -jar pepk.jar --keystore=ping2-joai-release-key.keystore --alias=key_ping_joai --output=output.zip --include-cert --rsa-aes-encryption --encryption-key-path=encryption_public_key.pem
keytool -export -rfc -keystore pin2Keystore.jks -alias key_ping_joai -file upload_certificate.pem
~/Android/Sdk/build-tools/34.0.0/apksigner rotate --out porfile --old-signer --ks pin2Keystore.jks --set-rollback true --new-signer --ks ping2-joai-release-key.keystore --set-rollback true --ks-key-alias JEsperancinhaORG
java -jar pepk.jar --keystore=ping2-joai-release-key.keystore --alias=JEsperancinhaORG --output=encrypted_private_key_path --rsa-aes-encryption --encryption-key-path=encryption_public_key.pem
java -jar pepk.jar --keystore=ping2-joai-release-key.keystore --alias=JEsperancinhaORG --output=output.zip --include-cert --rsa-aes-encryption --encryption-key-path=encryption_public_key.pem
keytool -export -rfc -keystore ping2-joai-release-key.keystore -alias key_ping_joai -file upload_certificate.pem
keytool -export -rfc -keystore ping2-joai-release-key.keystore -alias JEsperancinhaORG2 -file upload_certificate.pem
~/Android/Sdk/build-tools/34.0.0/apksigner rotate --out porfile --old-signer --ks pin2Keystore.jks --set-rollback true --new-signer --ks ping2-joai-release-key.keystore --set-rollback true --ks-key-alias JEsperancinhaORG2
~/Android/Sdk/build-tools/34.0.0/apksigner rotate --out porfile --old-signer --ks pin2Keystore.jks --set-rollback true --new-signer --ks ping2-joai-release-key.keystore --set-rollback true --ks-key-alias key_ping_joai
``

>This step is only necessary when creating your own private key. Google can do that for you


- Conversion (if necessary)

```shell
keytool -importkeystore -srckeystore pin2Keystore.jks -destkeystore ping2-joai-release-key.keystore
```