#!/bin/sh

set -e

NAME="authorization-server-cert-jwk"
ALIAS="key"

keytool -genkeypair -keyalg RSA -keysize 2048 -keystore "$NAME.jks" -validity 365 -alias "$ALIAS"
keytool -importkeystore -srckeystore "$NAME.jks" -destkeystore "$NAME.p12" -deststoretype PKCS12

rm "$NAME.jks"