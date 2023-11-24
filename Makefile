# Makefile for a Go project

# Variables

# Targets
all: test build

build:
	docker build -t porcupine .

run:
	docker run -v $(pwd):/app -p 8080:8080 porcupine

run-interactive:
	docker run -v $(pwd):/app -it porcupine bash

.PHONY: all build test clean run deps
