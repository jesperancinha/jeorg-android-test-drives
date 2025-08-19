SHELL := /bin/bash
GRADLE_VERSION ?= 9.0.0

b:

wrapper:
	gradle wrapper
update:
	./gradlew dependencyUpdates
