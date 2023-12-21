# Makefile for a Go project

# Variables

# Targets
all: test build

build:
	docker build -t porcupine .

run:
	docker run -v $(pwd):/app -p 8080:8080 porcupine

test:
	docker run -v $(pwd):/app porcupine gradle test

.PHONY: all build test clean run deps
