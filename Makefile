SHELL := /bin/bash
GRADLE_VERSION := 8.0.2

wrapper:
	gradle wrapper
buildw:
	gradle clean build test publishToMavenLocal
coverage:
	./gradlew clean build test jacocoTestReport -i
	gradle -i
lint:
	(yes "" 2>/dev/null || true) | sdkmanager --licenses
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
