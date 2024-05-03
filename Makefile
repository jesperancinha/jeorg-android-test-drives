SHELL := /bin/bash
GRADLE_VERSION := 8.7

b: buildw
wrapper:
	gradle wrapper
buildw:
	gradle clean build test publishToMavenLocal
coverage:
	./gradlew clean build test jacocoTestReport -i
	gradle -i
lint:
	curl --output sdk-tools-linux.zip https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip
	unzip -o sdk-tools-linux.zip
	# If this is necessary for a successful build, please close the PR and do not merge
	#(yes "" 2>/dev/null || true) | ./cmdline-tools/bin/sdkmanager --licenses --sdk_root=$ANDROID_HOME
	#(yes "" 2>/dev/null || true) | ./cmdline-tools/bin/sdkmanager "build-tools;34.0.0-rc3" --sdk_root=$ANDROID_HOME
	./gradlew lint test
upgrade:
	gradle wrapper --gradle-version $(GRADLE_VERSION) --validate-url
	cd jeorg-time-converter && gradle wrapper --gradle-version $(GRADLE_VERSION) --validate-url
upgrade-mac-os:
	brew upgrade gradle
upgrade-gradle:
	sudo apt upgrade
	sudo apt update
	export SDKMAN_DIR="$(HOME)/.sdkman"; \
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]]; \
	source "$(HOME)/.sdkman/bin/sdkman-init.sh"; \
	sdk update; \
	gradleOnlineVersion=$(shell curl -s https://services.gradle.org/versions/current | jq .version | xargs -I {} echo {}); \
	if [[ -z "$$gradleOnlineVersion" ]]; then \
		sdk install gradle $(GRADLE_VERSION); \
		sdk use gradle $(GRADLE_VERSION); \
	else \
		sdk install gradle $$gradleOnlineVersion; \
		sdk use gradle $$gradleOnlineVersion; \
		export GRADLE_VERSION=$$gradleOnlineVersion; \
	fi; \
	make upgrade
install-linux:
	sudo apt-get install jq
	sudo apt-get install curl
	curl https://services.gradle.org/versions/current
