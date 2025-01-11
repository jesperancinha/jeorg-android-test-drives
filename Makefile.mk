SHELL := /bin/bash
GRADLE_VERSION ?= 8.10.2

wrapper:
	gradle wrapper
update:
	./gradlew dependencyUpdates
