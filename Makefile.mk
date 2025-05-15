SHELL := /bin/bash
GRADLE_VERSION ?= 8.14

b:

wrapper:
	gradle wrapper
update:
	./gradlew dependencyUpdates
