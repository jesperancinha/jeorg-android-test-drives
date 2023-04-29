SHELL := /bin/bash
GRADLE_VERSION := 8.0.2

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
	(yes "" 2>/dev/null || true) | ./cmdline-tools/bin/sdkmanager --licenses --sdk_root=$ANDROID_HOME
	./gradlew lint test
upgrade:
	gradle wrapper --gradle-version $(GRADLE_VERSION)
	cd jeorg-time-converter && gradle wrapper --gradle-version $(GRADLE_VERSION)
upgrade-mac-os:
	brew upgrade gradle
upgrade-gradle:
	sudo apt upgrade
	sudo apt update
	export SDKMAN_DIR="$(HOME)/.sdkman"
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]] && source "$(HOME)/.sdkman/bin/sdkman-init.sh" &&	sdk update
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]] && source "$(HOME)/.sdkman/bin/sdkman-init.sh" &&	sdk install gradle $(GRADLE_VERSION)
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]] && source "$(HOME)/.sdkman/bin/sdkman-init.sh" &&	sdk use gradle $(GRADLE_VERSION)
