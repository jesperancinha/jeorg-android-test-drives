## Keystore steps

- Create a keystore

```shell
keytool -genkey -v -keystore matrix-joai-release-key.keystore -alias JEsperancinhaORG -keyalg RSA -keysize 2048 -validity 1000000
keytool -genkey -v -keystore matrix-joai-release-key.keystore -alias JEsperancinhaORG2 -keyalg RSA -keysize 2048 -validity 1000000
```

>Validity for 1000000

- Create private key


```shell
java -jar pepk.jar --keystore=matrix-joai-release-key.keystore --alias=JEsperancinhaORG --output=encrypted_private_key_path --rsa-aes-encryption --encryption-key-path=encryption_public_key.pem
java -jar pepk.jar --keystore=matrix-joai-release-key.keystore --alias=JEsperancinhaORG --output=output.zip --include-cert --rsa-aes-encryption --encryption-key-path=encryption_public_key.pem
keytool -export -rfc -keystore majoai.jks -alias majoai -file upload_certificate.pem
~/Android/Sdk/build-tools/34.0.0/apksigner rotate --out porfile --old-signer --ks majoai.jks --set-rollback true --new-signer --ks matrix-joai-release-key.keystore --set-rollback true --ks-key-alias JEsperancinhaORG
java -jar pepk.jar --keystore=matrix-joai-release-key.keystore --alias=JEsperancinhaORG --output=encrypted_private_key_path --rsa-aes-encryption --encryption-key-path=encryption_public_key.pem
java -jar pepk.jar --keystore=matrix-joai-release-key.keystore --alias=JEsperancinhaORG --output=output.zip --include-cert --rsa-aes-encryption --encryption-key-path=encryption_public_key.pem
keytool -export -rfc -keystore matrix-joai-release-key.keystore -alias majoai -file upload_certificate.pem
keytool -export -rfc -keystore matrix-joai-release-key.keystore -alias JEsperancinhaORG2 -file upload_certificate.pem
~/Android/Sdk/build-tools/34.0.0/apksigner rotate --out orfile --old-signer --ks majoai.jks --set-rollback true --new-signer --ks matrix-joai-release-key.keystore --set-rollback true --ks-key-alias JEsperancinhaORG2
~/Android/Sdk/build-tools/34.0.0/apksigner rotate --out porfile --old-signer --ks majoai.jks --set-rollback true --new-signer --ks matrix-joai-release-key.keystore --set-rollback true --ks-key-alias majoai
```

>This step is only necessary when creating your own private key. Google can do that for you


- Conversion (if necessary)

```shell
keytool -importkeystore -srckeystore majoai.jks -destkeystore ping2-joai-release-key.keystore
keytool -list -keystore majoai.jks 
```