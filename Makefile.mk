SHELL := /bin/bash
GRADLE_VERSION ?= 8.10.2

b:

wrapper:
	gradle wrapper
update:
	./gradlew dependencyUpdates
